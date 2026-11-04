import importlib.util
import os
from pathlib import Path
import subprocess
import tempfile
import unittest

spec = importlib.util.spec_from_file_location('release', Path(__file__).parents[1] / 'validate_release.py')
release = importlib.util.module_from_spec(spec)
spec.loader.exec_module(release)


class ReleaseValidationTest(unittest.TestCase):
    def setUp(self):
        # Windows tools can retain a read-only Git metadata handle briefly.
        # This only relaxes scratch-directory cleanup, never validation assertions.
        self.temp = tempfile.TemporaryDirectory(ignore_cleanup_errors=os.name == 'nt')
        self.addCleanup(self.temp.cleanup)
        self.repo = Path(self.temp.name)
        self.git('init', '-b', 'main')
        self.write_poms('1.2.3')
        self.commit()
        self.git('update-ref', 'refs/remotes/origin/main', 'HEAD')
        self.git('tag', '-a', 'v1.2.3', '-m', 'Synthetic release')

    def git(self, *args):
        return subprocess.check_output(['git', '-c', 'user.name=Synthetic Test', '-c', 'user.email=test@example.invalid',
            '-C', str(self.repo), *args], text=True, stderr=subprocess.PIPE).strip()

    def write_poms(self, version, child_version=None):
        for path in release.POMS:
            node = f'<version>{version}</version>' if path == 'pom.xml' else f'<parent><version>{child_version or version}</version></parent>'
            p = self.repo / path
            p.parent.mkdir(parents=True, exist_ok=True)
            p.write_text(f'<project xmlns="http://maven.apache.org/POM/4.0.0">{node}</project>', encoding='utf-8')

    def commit(self):
        self.git('add', '.')
        self.git('commit', '-m', 'Synthetic fixture')

    def test_valid_annotated_tag_returns_commit(self):
        self.assertEqual(self.git('rev-parse', 'HEAD'), release.validate(self.repo, 'v1.2.3', 'github'))
        self.assertEqual(self.git('rev-parse', 'HEAD'), release.validate(self.repo, 'v1.2.3', 'central'))

    def test_rejects_shell_and_annotation_inputs_and_unknown_registry(self):
        for tag in ['--help', 'v1.2.3; echo injected', 'v1.2.3\n::error::injected', 'v1.2.3-SNAPSHOT', 'v01.2.3', 'main']:
            with self.subTest(tag=tag), self.assertRaises(ValueError):
                release.validate(self.repo, tag, 'github')
        with self.assertRaises(ValueError):
            release.validate(self.repo, 'v1.2.3', 'untrusted-registry')

    def test_rejects_commit_outside_main(self):
        self.git('switch', '--orphan', 'foreign')
        self.write_poms('2.0.0')
        self.commit()
        self.git('tag', 'v2.0.0')
        with self.assertRaises(ValueError):
            release.validate(self.repo, 'v2.0.0', 'github')

    def test_rejects_snapshot_and_inconsistent_child_versions(self):
        for version, child in [('2.0.0-SNAPSHOT', None), ('2.0.0', '1.2.3')]:
            with self.subTest(version=version, child=child):
                self.write_poms(version, child)
                self.commit()
                self.git('update-ref', 'refs/remotes/origin/main', 'HEAD')
                self.git('tag', '-f', 'v2.0.0')
                with self.assertRaises(ValueError):
                    release.validate(self.repo, 'v2.0.0', 'central')

    def test_rejects_missing_tag(self):
        with self.assertRaises(subprocess.CalledProcessError):
            release.validate(self.repo, 'v9.9.9', 'github')


if __name__ == '__main__':
    unittest.main()

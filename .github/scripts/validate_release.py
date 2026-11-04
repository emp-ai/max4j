"""Validate trusted release inputs before a job receives publication credentials."""
from pathlib import Path
import os
import re
import subprocess
import sys
import xml.etree.ElementTree as ET

NS = {'m': 'http://maven.apache.org/POM/4.0.0'}
POMS = ('pom.xml', 'core/pom.xml', 'spring-boot-3-starter/pom.xml')


def git(repo, *args):
    return subprocess.check_output(['git', '-C', str(repo), *args], text=True, stderr=subprocess.PIPE).strip()


def validate(repo, tag, registry):
    if registry not in {'github', 'central'}:
        raise ValueError('Unsupported publication registry')
    if not re.fullmatch(r'v(?:0|[1-9][0-9]*)\.(?:0|[1-9][0-9]*)\.(?:0|[1-9][0-9]*)', tag):
        raise ValueError('Expected a stable release tag vX.Y.Z')
    # Never pass an unvalidated user input as an option or execute it in a shell.
    sha = git(repo, 'rev-parse', '--verify', f'refs/tags/{tag}^{{commit}}')
    if not re.fullmatch(r'[0-9a-f]{40,64}', sha):
        raise ValueError('Invalid tag commit')
    result = subprocess.run(['git', '-C', str(repo), 'merge-base', '--is-ancestor', sha,
                             'refs/remotes/origin/main'], capture_output=True)
    if result.returncode != 0:
        raise ValueError('The release commit must be part of origin/main')
    version = tag[1:]
    for path in POMS:
        pom = ET.fromstring(git(repo, 'show', f'{sha}:{path}'))
        actual = pom.findtext('m:version' if path == 'pom.xml' else 'm:parent/m:version', namespaces=NS)
        own = pom.findtext('m:version', namespaces=NS)
        if actual != version or (own is not None and own != version):
            raise ValueError('All tagged POM versions must match the stable release tag')
    return sha


def main():
    try:
        sha = validate(Path.cwd(), os.environ.get('RELEASE_TAG', ''), os.environ.get('RELEASE_REGISTRY', ''))
    except (ValueError, subprocess.CalledProcessError, ET.ParseError):
        # Do not echo untrusted input into workflow commands/annotations.
        print('Release rejected: use an existing vX.Y.Z tag on main with matching non-SNAPSHOT POM versions.', file=sys.stderr)
        return 1
    output = os.environ.get('GITHUB_OUTPUT')
    if output:
        with open(output, 'a', encoding='utf-8') as stream:
            stream.write(f'sha={sha}\n')
    print(f'Validated release commit {sha}')
    return 0


if __name__ == '__main__':
    sys.exit(main())

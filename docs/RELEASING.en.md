# Releases and repository security

[Русский](RELEASING.md)

## Contribution checks

`Build / verify` runs Maven tests and release-validation tests on Java 21.
Dependency review rejects newly introduced high/critical advisories in PR dependency changes.
Dependabot checks Maven and GitHub Actions weekly. CodeQL default setup analyzes Java and workflows.
Actions are pinned to full commit SHAs. External PR CI needs maintainer approval.

`main` requires a PR, the `verify` and `dependency-review` checks, review/code-owner review
and resolved conversations.
Administrators have a PR-only bypass so a sole maintainer can merge their own verified PR;
they cannot bypass these rules with a direct push. Never use bypass to ignore failed checks.
`v*` tags cannot be rewritten or deleted. Public visibility does not grant write access.
Publication is not triggered by a PR or an ordinary push to `main`.

## GitHub Packages release

1. Update all three POM versions to non-SNAPSHOT `X.Y.Z` in a PR. Run the full build.
2. Merge the reviewed PR. Create and push tag `vX.Y.Z` at that reviewed commit on `main`.
3. Run **Publish Maven package** from `main` with that tag and registry `github`.
4. Validation rejects malformed/missing tags, commits outside `main`, snapshots and inconsistent POMs.
5. Approve the `release` environment as a maintainer. Self-review is allowed for a sole maintainer;
   environment protections cannot be bypassed by admins.
6. The full reactor is verified before any module is deployed. Publish uses the short-lived
   `GITHUB_TOKEN` with `packages: write`, without restoring a Maven cache.

Never replace a stable version: bump it for corrections. The current `0.1.0-SNAPSHOT` is a
development version, not a Central release. GitHub Maven downloads require authentication.

## Maven Central prerequisites

`io.bittersweet.max` is preserved to avoid silently changing consumers' coordinates.
Publishing requires verification of the corresponding namespace.

1. Create an account at https://central.sonatype.com/.
2. Verify a namespace you control. Follow Central's domain/namespace verification for `io.bittersweet.max`.
   If you do not control it, choose a verified namespace (for example a GitHub-derived namespace)
   and migrate all modules, examples and consumers together. Owning `emp-ai/max4j` does not prove
   ownership of `bittersweet.io`.
3. Generate a Portal user token. Store its username/password as `CENTRAL_USERNAME` and `CENTRAL_PASSWORD`
   in GitHub **release environment secrets**, not in the repository or POM.
4. Create an OpenPGP signing key, publish its public key as required by Central, and store the exported
   private key in `MAVEN_GPG_KEY` and passphrase in `MAVEN_GPG_PASSPHRASE` in that environment.
   Follow Maven GPG's documented format/encoding for the BC signer. Never paste keys in issues.
5. Prepare a reviewed tag as above, then run publication with registry `central`.
   `central-release` supplies sources, Javadoc, signatures and required POM metadata.
6. `autoPublish=false`: the signed deployment is uploaded for validation/review in Portal.
   Review and publish it there. Registration, namespace verification and first publication
   are separate steps; they have not been performed by this repository setup.

Ordinary builds do not load signing credentials or this publishing profile.

## References

- [Central requirements](https://central.sonatype.org/publish/requirements/)
- [Central Maven plugin](https://central.sonatype.org/publish/publish-portal-maven/)
- [Central namespaces](https://central.sonatype.org/register/namespace/)
- [Maven GPG signer](https://maven.apache.org/plugins/maven-gpg-plugin/sign-mojo.html)
- [GitHub Maven authentication](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry)

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

The owner confirmed the `io.github.bababebr` namespace in Central. Modules use the child
`groupId` `io.github.bababebr.max4j`. No DNS verification of `io.bittersweet.max` is needed.
The Central profile has been built locally with signing disabled; signing and upload remain untested.

1. Sign in at https://central.sonatype.com/ with publishing access to `io.github.bababebr`.
   A new publishing account must obtain access to that namespace first.
2. Open https://central.sonatype.com/usertoken, choose **Generate User Token**, set its name and
   expiration, and save both generated values before closing the dialog.
   Store its username/password as `CENTRAL_USERNAME` and `CENTRAL_PASSWORD`
   in GitHub **release environment secrets**, not in the repository or POM.
3. Create an OpenPGP signing key, publish its public key as required by Central, and store the exported
   private key in `MAVEN_GPG_KEY` and passphrase in `MAVEN_GPG_PASSPHRASE` in that environment.
   Follow Maven GPG's documented format/encoding for the BC signer. Never paste keys in issues.
4. Prepare a reviewed tag as above, then run publication with registry `central`.
   `central-release` supplies sources, Javadoc, signatures and required POM metadata.
5. `autoPublish=false`: the signed deployment is uploaded for validation/review in Portal.
   Review and publish it there. This repository setup has not published a Central release.

Ordinary builds do not load signing credentials or this publishing profile.

## Verify signing without publishing

After adding the four secrets to the `release` environment, run **Actions → Verify Central signing →
Run workflow** from `main`. Approve its protected environment job in **Review deployments**.
It uses the release profile and BC signer to build and sign all POMs, JARs, sources and Javadoc.
It then retrieves the public key from `keyserver.ubuntu.com` and verifies every signature with GPG.
Secret values are not printed. The job has read-only repository access and does not use the Central token.

This check permits a SNAPSHOT by skipping the Maven Enforcer release-version requirement.
The actual publication workflow still requires stable, matching versions and a validated tag.
The verification workflow has no `deploy` goal and does not create releases or upload packages.

## Consumer migration

Changing Maven coordinates does not rename the Java packages `io.bittersweet.max.*` or
the `max4j.*` configuration properties. After the first publication, change consumers'
max4j dependency `groupId` from `io.bittersweet.max` to `io.github.bababebr.max4j` and select
the published version. Do not include both coordinates: they contain the same Java classes.
Existing consumers can keep the previously published coordinates until the new release is available.
See [namespace and groupId rules](https://central.sonatype.org/faq/namespaces-vs-groupids/).

## References

- [Central requirements](https://central.sonatype.org/publish/requirements/)
- [Central Maven plugin](https://central.sonatype.org/publish/publish-portal-maven/)
- [Central namespaces](https://central.sonatype.org/register/namespace/)
- [Maven GPG signer](https://maven.apache.org/plugins/maven-gpg-plugin/sign-mojo.html)
- [GitHub Maven authentication](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry)

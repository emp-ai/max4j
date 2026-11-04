# Contributing to max4j

Contributions are welcome through a fork and pull request targeting `main`.
Discuss substantial API changes in an issue first. An internal tracker ID is not required.

## Build and tests

Install Java 21 and run `./mvnw --batch-mode verify` (Windows: `mvnw.cmd --batch-mode verify`).
Maven Wrapper downloads Maven and dependencies. Unit tests use synthetic values; no MAX bot token is needed.
Run release guard tests with `python3 -m unittest discover -s .github/scripts/tests -v`.

The core client/models are generated from `core/src/main/openapi/max-api.yaml` during the build.
Change the schema or generator configuration rather than editing generated files in `target/`.
Document the upstream version/commit and retain its license and attribution when updating it.

Add focused tests for behavior changes, preserve public APIs where possible,
and update examples for deliberate breaking changes. Use descriptive titles such as
`fix: validate webhook secret` or `feat: support a new update type`.

## Pull requests and CI

Explain the problem and resulting behavior, then describe validation.
CI runs on GitHub-hosted runners with read-only repository access. External contributors' workflow
runs require maintainer approval; this is separate from review/merge approval.
Review workflow, Maven plugin, wrapper and dependency changes as executable code.
Never include real credentials, customer messages, or production configuration in a PR or issue.

Maintainers merge after tests and review. Dependency updates are not automatically approved.
Protected `main` requires a PR and CI; critical files have code owners.
Administrators can use an audited PR-only bypass when maintaining their own PRs;
it does not permit direct pushes or routine merging of failed CI.

## Licensing

The project is licensed under Apache-2.0. By submitting a contribution, you agree to license
it under Apache-2.0 and confirm you have the rights to submit it.
Keep third-party notices and identify copied/generated third-party material.
No separate CLA or DCO check is currently required.

See [SECURITY.md](SECURITY.md) and [docs/RELEASING.md](docs/RELEASING.md).

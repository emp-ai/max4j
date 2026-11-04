# Security policy

Report vulnerabilities through [GitHub private vulnerability reporting](https://github.com/emp-ai/max4j/security/advisories/new).
Include the affected version, impact and a minimal reproduction with synthetic data.
Do not publish credentials, user conversations or exploit details in a public issue/PR.

Security fixes target the current development branch and latest supported release.
Older versions do not have a guaranteed backport policy. This early-stage project does not
promise a fixed response/resolution SLA or a bug bounty.

Load MAX bot tokens and webhook secrets from your application's secret store/environment.
For production webhooks, enable and verify the webhook secret over HTTPS.
Rotate exposed credentials even if a commit containing them has been removed from branch history.

Contribution CI has no publication credentials. Releases use a separate protected environment
and immutable release tags. See [the release guide](docs/RELEASING.md).

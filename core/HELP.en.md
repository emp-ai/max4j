# Developing the core module

[Русский](HELP.md)

`core` contains the MAX Bot API client, models and API classes without a Spring dependency.
The schema version is documented in the [README](../README.md); its source is
`src/main/openapi/max-api.yaml`.

Build from the repository root on Java 21:

```bash
./mvnw --batch-mode verify
```

To build only `core` and its required modules, use
`./mvnw --batch-mode -pl core -am verify`.
On Windows, replace `./mvnw` with `mvnw.cmd`.

OpenAPI Generator creates sources in `target/` during the build. Do not edit or commit
them; change the source schema or generator configuration in the POM instead.
Tests do not need a bot token. See [CONTRIBUTING.en.md](../CONTRIBUTING.en.md).

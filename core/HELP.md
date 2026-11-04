# Разработка модуля core

[English](HELP.en.md)

`core` содержит клиент MAX Bot API, модели и API-классы. Модуль не зависит от Spring.
Версия схемы указана в [README](../README.md); сам файл — `src/main/openapi/max-api.yaml`.

Собирайте проект из корня репозитория на Java 21:

```bash
./mvnw --batch-mode verify
```

Для сборки только `core` и необходимых ему модулей используйте
`./mvnw --batch-mode -pl core -am verify`.
В Windows замените `./mvnw` на `mvnw.cmd`.

OpenAPI Generator создаёт исходники в `target/` при сборке. Не редактируйте их вручную
и не добавляйте в Git: меняйте исходную схему или настройки генерации в POM.
Для тестов токен бота не требуется. Правила участия — в [CONTRIBUTING.md](../CONTRIBUTING.md).

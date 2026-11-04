# max4j

[![Build](https://github.com/emp-ai/max4j/actions/workflows/build.yml/badge.svg)](https://github.com/emp-ai/max4j/actions/workflows/build.yml)
[![License: Apache-2.0](https://img.shields.io/badge/license-Apache--2.0-blue.svg)](LICENSE)

Java 21 клиент для [MAX Bot API](https://dev.max.ru/docs-api), сгенерированный из официальной
[OpenAPI-схемы](https://github.com/max-messenger/api-schema), и автоконфигурация для Spring Boot 3.

Независимый проект под Apache-2.0. [Участие в разработке](CONTRIBUTING.md) ·
[Сообщить об уязвимости](SECURITY.md).

Maven-координаты: **`io.github.bababebr.max4j`**. Зависимости подключаются из Maven Central.

## Модули

- `core` — модели, API-классы и HTTP-клиент без зависимости от Spring;
- `spring-boot-3-starter` — автоконфигурация клиента и опциональный webhook-контроллер.

В репозитории зафиксирована схема MAX Bot API версии `0.0.33`. Java-код API и моделей
генерируется во время сборки с помощью OpenAPI Generator.

## Требования

- Java 21;
- Maven 3.9+ или Maven Wrapper из репозитория;
- токен бота из MAX для реальных запросов.

## Core

```xml
<dependency>
  <groupId>io.github.bababebr.max4j</groupId>
  <artifactId>core</artifactId>
  <version>0.1.1</version>
</dependency>
```

```java
import io.bittersweet.max.core.MaxClient;
import io.bittersweet.max.core.model.BotInfo;

MaxClient max = MaxClient.create(System.getenv("MAX_BOT_TOKEN"));
BotInfo me = max.bots().getMyInfo();
System.out.println(me.getUsername());
```

Через `MaxClient` доступны группы `bots`, `chats`, `comments`, `messages`, `subscriptions`
и `uploads`. Токен передаётся как значение заголовка `Authorization`, как требует MAX API,
без префикса `Bearer`.

## Spring Boot 3 starter

```xml
<dependency>
  <groupId>io.github.bababebr.max4j</groupId>
  <artifactId>spring-boot-3-starter</artifactId>
  <version>0.1.1</version>
</dependency>
```

Минимальная конфигурация:

```yaml
max4j:
  enabled: true
  token: ${MAX_BOT_TOKEN}
```

Starter создаёт `MaxClient`, низкоуровневый `ApiClient` и beans всех API-групп. При необходимости
можно переопределить адрес и таймауты:

```yaml
max4j:
  enabled: true
  token: ${MAX_BOT_TOKEN}
  base-url: https://platform-api2.max.ru
  connect-timeout: 10s
  read-timeout: 30s
```

## Webhook

Webhook-контроллер включается только в servlet-приложении, если есть хотя бы один
`MaxUpdateHandler`:

```yaml
max4j:
  enabled: true
  token: ${MAX_BOT_TOKEN}
  webhook:
    enabled: true
    path: /max/webhook
    secret: ${MAX_WEBHOOK_SECRET}
```

```java
import io.bittersweet.max.springboot.webhook.MaxUpdateHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MaxBotConfiguration {

  @Bean
  MaxUpdateHandler maxUpdates() {
    return update -> System.out.println(update.getUpdateType());
  }
}
```

Если `secret` задан, starter сравнивает его с заголовком `X-Max-Bot-Api-Secret` и отклоняет
запросы с неверным значением. После публикации HTTPS endpoint нужно один раз зарегистрировать
подписку в MAX с тем же секретом:

```java
import io.bittersweet.max.core.model.SubscriptionRequestBody;
import io.bittersweet.max.core.api.SubscriptionsApi;

subscriptionsApi.subscribe(new SubscriptionRequestBody()
    .url("https://example.org/max/webhook")
    .secret(System.getenv("MAX_WEBHOOK_SECRET")));
```

MAX рекомендует webhook для production. Long polling остаётся доступен через
`SubscriptionsApi#getUpdates` для разработки и тестов. Webhook и long polling нельзя
использовать одновременно.

## Обновление схемы

Замените `core/src/main/openapi/max-api.yaml` актуальным файлом из официального репозитория и
запустите `mvnw verify`. Сгенерированные исходники находятся в `core/target` и не коммитятся.

## Сборка и релиз

```bash
./mvnw --batch-mode verify
```

В Windows используйте `mvnw.cmd`. Для релиза обновите версии во всех POM, отправьте тег
`vX.Y.Z` на проверенный коммит `main` и запустите **Actions → Publish Maven package**
с этим тегом и реестром `central`. Подтвердите окружение `release` в **Review deployments**.
Workflow подписывает артефакты, публикует их в Maven Central и ждёт окончания публикации.

В секретах окружения `release` нужны `CENTRAL_USERNAME`, `CENTRAL_PASSWORD`
из [токена Portal](https://central.sonatype.com/usertoken), а также `MAVEN_GPG_KEY`
(экспорт закрытого ключа в ASCII armor) и `MAVEN_GPG_PASSPHRASE`. Открытый ключ должен быть
доступен на keyserver. Локальный `-Pcentral-release deploy` по умолчанию только загружает
пакет для проверки в Portal; публикацию подтверждают отдельно.

## Переход со старых координат

Замените Maven `groupId` `io.bittersweet.max`
на `io.github.bababebr.max4j` у зависимостей max4j. `artifactId` модулей сохраняются.
Java-пакеты `io.bittersweet.max.*` и настройки `max4j.*` сохраняются, поэтому импорты
и конфигурацию приложения переименовывать не нужно. Не подключайте одновременно
старые и новые координаты: они содержат одинаковые Java-классы.

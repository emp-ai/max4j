# max4j

Java 21 клиент для [MAX Bot API](https://dev.max.ru/docs-api), сгенерированный из официальной
[OpenAPI-схемы](https://github.com/max-messenger/api-schema), и автоконфигурация для Spring Boot 3.

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
  <groupId>io.bittersweet.max</groupId>
  <artifactId>core</artifactId>
  <version>0.1.0-SNAPSHOT</version>
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
  <groupId>io.bittersweet.max</groupId>
  <artifactId>spring-boot-3-starter</artifactId>
  <version>0.1.0-SNAPSHOT</version>
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

## Сборка

Linux/macOS:

```shell
./mvnw verify
```

Windows:

```powershell
.\mvnw.cmd verify
```

## Обновление схемы

Замените `core/src/main/openapi/max-api.yaml` актуальным файлом из официального репозитория и
запустите `mvnw verify`. Сгенерированные исходники находятся в `core/target` и не коммитятся.

## Публикация

В parent POM настроен GitHub Packages. Для deploy нужны `GITHUB_ACTOR` и токен с правом
`write:packages`, переданные Maven-серверу с id `github`.

Не храните токен бота или webhook secret в репозитории — передавайте их через переменные
окружения или менеджер секретов.

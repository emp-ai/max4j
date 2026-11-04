package io.bittersweet.max.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.bittersweet.max.core.model.BotStartedUpdate;
import io.bittersweet.max.core.model.Update;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import org.junit.jupiter.api.Test;

class MaxClientTest {

  @Test
  void configuresRawAccessTokenAndBaseUrl() {
    MaxClient client = MaxClient.builder("test-token")
        .baseUrl("https://example.test/")
        .build();
    HttpRequest.Builder request = HttpRequest.newBuilder(URI.create("https://example.test/me"));

    client.apiClient().getRequestInterceptor().accept(request);

    assertEquals("test-token", request.build().headers().firstValue("Authorization").orElseThrow());
    assertEquals("https://example.test", client.apiClient().getBaseUri().toString());
  }

  @Test
  void deserializesPolymorphicWebhookUpdate() throws IOException {
    MaxClient client = MaxClient.create("test-token");
    String payload = """
        {
          "update_type":"bot_started",
          "timestamp":1573226679188,
          "chat_id":1234567890,
          "user":{"user_id":1234567890,"first_name":"Boris","username":"borisd84","is_bot":false},
          "payload":"start-data"
        }
        """;

    Update update = client.apiClient().getObjectMapper().readValue(payload, Update.class);

    BotStartedUpdate started = assertInstanceOf(BotStartedUpdate.class, update);
    assertEquals(1234567890L, started.getChatId());
    assertEquals("start-data", started.getPayload());
  }

  @Test
  void rejectsBlankToken() {
    assertThrows(IllegalArgumentException.class, () -> MaxClient.create(" "));
  }
}

package io.bittersweet.max.springboot.webhook;

import io.bittersweet.max.core.model.Update;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public final class MaxWebhookController {

  private final List<MaxUpdateHandler> handlers;
  private final byte[] expectedSecret;

  public MaxWebhookController(List<MaxUpdateHandler> handlers, String secret) {
    this.handlers = List.copyOf(handlers);
    this.expectedSecret = secret == null || secret.isBlank()
        ? null
        : secret.getBytes(StandardCharsets.UTF_8);
  }

  @PostMapping("${max4j.webhook.path:/max4j/webhook}")
  public void receive(
      @RequestHeader(value = "X-Max-Bot-Api-Secret", required = false) String secret,
      @RequestBody Update update) {
    if (!hasValidSecret(secret)) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid MAX webhook secret");
    }
    handlers.forEach(handler -> handler.handle(update));
  }

  private boolean hasValidSecret(String actualSecret) {
    if (expectedSecret == null) {
      return true;
    }
    if (actualSecret == null) {
      return false;
    }
    return MessageDigest.isEqual(
        expectedSecret,
        actualSecret.getBytes(StandardCharsets.UTF_8));
  }
}

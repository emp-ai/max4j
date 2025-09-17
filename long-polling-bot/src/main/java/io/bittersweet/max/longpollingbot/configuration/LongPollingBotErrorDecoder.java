package io.bittersweet.max.longpollingbot.configuration;

import feign.Response;
import feign.codec.ErrorDecoder;
import io.bittersweet.max.core.exception.MaxBotException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "bittersweet.max.long-polling.enabled")
public class LongPollingBotErrorDecoder implements ErrorDecoder {

  private final ErrorDecoder defaultErrorDecoder = new Default();

  @Override
  public Exception decode(String methodKey, Response response) {
    if (response.status() >= 400) {
      return new MaxBotException(
          response.status(),
          response.status() + " " + response.reason(),
          response.request().httpMethod(),
          null,
          null,
          response.request()
      );
    }
    return defaultErrorDecoder.decode(methodKey, response);
  }
}

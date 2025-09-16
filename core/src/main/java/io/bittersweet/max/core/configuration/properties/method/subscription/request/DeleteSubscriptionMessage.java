package io.bittersweet.max.core.configuration.properties.method.subscription.request;

import io.bittersweet.max.core.configuration.properties.method.BotMethod;
import io.bittersweet.max.core.configuration.properties.method.enums.MethodType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

/**
 * Signs up the bot to receive updates via the WebHook. After calling this method, the bot will receive notifications about new chat events
 * at the specified URL. Your server must listen on one of the following ports: 80, 8080, 443, 8443, 16384-32383.
 */
@Value
@Builder
@EqualsAndHashCode(callSuper = true)
public class DeleteSubscriptionMessage extends BotMethod {

  /** The URL of your bot's HTTP(S) endpoint. Must start with http(s):// */
  @NotNull
  String url;

  @Override
  public MethodType getMethod() {
    return MethodType.SUBSCRIPTION_DELETE;
  }
}

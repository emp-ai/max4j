package io.bittersweet.max.core.method.subscription.request;

import io.bittersweet.max.core.method.BotMethod;
import io.bittersweet.max.core.method.enums.MethodType;
import io.bittersweet.max.core.method.enums.UpdateType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;
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
public class AddSubscriptionMessage extends BotMethod {

  /** The URL of your bot's HTTP(S) endpoint. Must start with http(s):// */
  @NotNull
  String url;

  /**
   * Example: ["message_created", "bot_started"]
   * <p>
   * A list of the types of updates your bot wants to receive. For a complete list of types, see the Update object.
   */
  @NotNull
  List<UpdateType> updateTypes;

  /**
   * The secret that should be sent in the X-Max-Bot-Api-Secret header in each Webhook request. Only the characters A-Z, a-z, 0-9, and
   * hyphen are allowed. The header is recommended so that the request comes from an installed website
   */
  @Nullable
  @Size(min = 5, max = 256)
  @Pattern(regexp = "^[a-zA-Z0-9_-]{5,256}$", message = "Webhook secret format is wrong. Check format and length")
  String secret;

  @Override
  public MethodType getMethod() {
    return MethodType.SUBSCRIPTION_ADD;
  }
}

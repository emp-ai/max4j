package io.bittersweet.max.core.configuration.properties.method.chat.request;

import io.bittersweet.max.core.configuration.properties.method.BotMethod;
import io.bittersweet.max.core.configuration.properties.method.enums.MethodType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
public class DiscardAdminMessage extends BotMethod {

  @NotNull
  @Pattern(regexp = "\\-?\\d+", message = "Chat id format is wrong")
  Long chatId;

  /** User id */
  @NotNull
  Long userId;

  @Override
  public MethodType getMethod() {
    return MethodType.DISCARD_ADMIN_RIGHT;
  }

}

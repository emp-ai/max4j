package io.bittersweet.max.core.method.chat.request;

import io.bittersweet.max.core.method.BotMethod;
import io.bittersweet.max.core.method.enums.MethodType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
public class PinMessage extends BotMethod {

  /** Chat Id */
  @NotNull
  @Pattern(regexp = "\\-?\\d+", message = "Chat id format is wrong")
  Long chatId;

  /** Message id which need to be pinned in chat */
  @NotNull
  String messageId;

  /** If true, participants will receive a system notification about the change. Default - true */
  @Nullable
  Boolean notify;

  @Override
  public MethodType getMethod() {
    return MethodType.PIN_MESSAGE;
  }
}

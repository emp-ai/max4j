package io.bittersweet.max.core.method.chat.request;

import io.bittersweet.max.core.method.BotMethod;
import io.bittersweet.max.core.method.enums.MethodType;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
public class ChatMemberMessage extends BotMethod {

  @NotNull
  @Pattern(regexp = "\\-?\\d+", message = "Chat id format is wrong")
  Long chatId;

  @Override
  public MethodType getMethod() {
    return MethodType.CHAT_MEMBER;
  }

}

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
public class DeleteChatMemberMessage extends BotMethod {

  @NotNull
  @Pattern(regexp = "\\-?\\d+", message = "Chat id format is wrong")
  Long chatId;

  /** User to deleted to the chat */
  @NotNull
  Long userIds;

  /**
   * If set to true, the user will be blocked in the chat. It is used only for chats with a public or private link. Ignored in all other
   * cases.
   */
  @Nullable
  Boolean blocked;

  @Override
  public MethodType getMethod() {
    return MethodType.GET_CHAT_MEMBERS;
  }
}

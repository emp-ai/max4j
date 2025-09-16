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
public class ChatByLinkMessage extends BotMethod {

  /** Public link to the chat or the user's username */
  @NotNull
  @Pattern(regexp = "@?[a-zA-Z]+[a-zA-Z0-9-_]*", message = "Invalid chat link")
  String chatLink;

  @Override
  public MethodType getMethod() {
    return MethodType.CHAT_BY_LINK;
  }
}

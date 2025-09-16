package io.bittersweet.max.core.method.chat.request;

import io.bittersweet.max.core.method.BotMethod;
import io.bittersweet.max.core.method.enums.ChatType;
import io.bittersweet.max.core.method.enums.MethodType;
import io.bittersweet.max.core.method.model.image.PhotoAttachmentRequestPayload;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
public class EditChatMessage extends BotMethod {

  /** Chat Id */
  @NotNull
  @Pattern(regexp = "\\-?\\d+", message = "Chat id format is wrong")
  Long chatId;

  /** The displayed chat name. Could be null for {@link ChatType#DIALOG} */
  @Nullable
  @Size(min = 1, max = 200)
  String title;

  /** Chat icon */
  @Nullable
  PhotoAttachmentRequestPayload icon;

  /** The ID of the message to pin in the chat. To delete a pinned message, use the unpin method */
  @Nullable
  String pin;

  /** If true, participants will receive a system notification about the change. Default - true */
  @Nullable
  Boolean notify;

  @Override
  public MethodType getMethod() {
    return MethodType.CHAT_EDIT_BY_ID;
  }
}

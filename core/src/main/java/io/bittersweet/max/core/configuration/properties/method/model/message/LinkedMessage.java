package io.bittersweet.max.core.configuration.properties.method.model.message;

import io.bittersweet.max.core.configuration.properties.method.enums.MessageLinkType;
import io.bittersweet.max.core.configuration.properties.method.model.user.User;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
public class LinkedMessage {

  /** Message type */
  @NotNull
  MessageLinkType type;

  /** User who sent message */
  @Nullable
  User sender;

  /** The chat where the message was originally posted. For forwarded messages only */
  @Nullable
  Long chatId;

  /** Message body */
  @NotNull
  MessageBody message;
}

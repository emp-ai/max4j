package io.bittersweet.max.core.configuration.properties.method.model.message;

import io.bittersweet.max.core.configuration.properties.method.model.reipient.Recipient;
import io.bittersweet.max.core.configuration.properties.method.model.user.User;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
public class Message {

  /** User who sent message */
  @NotNull
  User sender;

  /** The recipient of the message. It can be a user or a chat */
  @NotNull
  Recipient recipient;

  /** Message sent time in millis */
  @NotNull
  Long timestamp;

  /** Forwarded or reply message */
  @Nullable
  LinkedMessage link;

  /** Message content */
  @Nullable
  MessageBody body;

  /** Message stat */
  @Nullable
  MessageStat stat;

  /** A public link to the message. It can be null for dialogs or non-public chats. */
  @Nullable
  String url;
}

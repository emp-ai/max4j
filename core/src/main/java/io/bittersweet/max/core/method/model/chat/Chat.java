package io.bittersweet.max.core.method.model.chat;

import io.bittersweet.max.core.method.BotResponse;
import io.bittersweet.max.core.method.enums.ChatStatus;
import io.bittersweet.max.core.method.enums.ChatType;
import io.bittersweet.max.core.method.model.image.Image;
import io.bittersweet.max.core.method.model.message.Message;
import io.bittersweet.max.core.method.model.user.UserWithPhoto;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
public class Chat implements BotResponse {

  /** Chat ID */
  @NotNull
  String chatId;

  /** Chat type */
  @NotNull
  ChatType type;

  /** Chat status */
  @NotNull
  ChatStatus status;

  /** The displayed chat name. Could be null for {@link ChatType#DIALOG} */
  @Nullable
  String title;

  /** Chat icon */
  @Nullable
  Image icon;

  /** Timestamp of the last event in the chat */
  @NotNull
  Long lastEventTime;

  /** Number of chat participants. For {@link ChatType#DIALOG} equals 2 */
  @NotNull
  Integer participantsCount;

  /** Chat owner id */
  @Nullable
  Long ownerId;

  //TODO Check, confusing doc
  /** Chat participants with the last activity time. It can be null if a list of chats is requested */
  @Nullable
  Object participants;

  /** Is the chat publicly available (always false for {@link ChatType#DIALOG}) */
  @NotNull
  Boolean isPublic;

  /** Chat link */
  @Nullable
  String link;

  /** Chat description */
  @Nullable
  String description;

  /** User information in the dialog (only for {@link ChatType#DIALOG}) */
  @Nullable
  UserWithPhoto dialogWithUser;

  /** The ID of the message containing the button through which the chat was initiated */
  @Nullable
  String chatMessageId;

  /** Pinned chat message (returned only when a specific chat is requested) */
  @Nullable
  Message pinned_message;
}

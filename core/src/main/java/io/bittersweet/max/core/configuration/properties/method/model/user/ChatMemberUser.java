package io.bittersweet.max.core.configuration.properties.method.model.user;

import io.bittersweet.max.core.configuration.properties.method.enums.ChatAdminPermission;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ChatMemberUser extends User {

  /** User's description, max length = 16000 */
  @Nullable
  @Size(max = 16000)
  String description;

  /** User's avatar URL */
  @Nullable
  String avatar_url;

  /** High-res user's avatar URL */
  @Nullable
  String full_avatar_url;

  /** Last activity time in millis */
  @NotNull
  Long lastAccessTime;

  /** Is user chat owner */
  @NotNull
  Boolean isOwner;

  /** Is user chat admin */
  @NotNull
  Boolean isAdmin;

  /** Time user join chat in millis */
  @NotNull
  Long joinTime;

  /** User rights */
  @Nullable
  List<ChatAdminPermission> permissions;

  /**
   * The header that will be shown on the client
   * <p>
   * If the user is an administrator or owner and this name is not set for him, then the field is not transmitted, the clients on their side
   * will replace it with "owner" or "admin"
   */
  @Nullable
  String alias;
}

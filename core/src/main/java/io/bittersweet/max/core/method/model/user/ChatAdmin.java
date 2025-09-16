package io.bittersweet.max.core.method.model.user;

import io.bittersweet.max.core.method.enums.ChatAdminPermission;
import java.util.List;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
public class ChatAdmin {

  /** User ID */
  @NotNull
  Long userId;

  /** User rights */
  @NotNull
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

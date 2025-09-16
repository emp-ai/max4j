package io.bittersweet.max.core.configuration.properties.method.chat.response;

import io.bittersweet.max.core.configuration.properties.method.BotResponse;
import io.bittersweet.max.core.configuration.properties.method.model.user.ChatMemberUser;
import java.util.List;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
public class ChatAdminsResponse implements BotResponse {

  /** Admins list */
  @NotNull
  List<ChatMemberUser> members;

  /** Next available page number */
  @Nullable
  Long marker;
}

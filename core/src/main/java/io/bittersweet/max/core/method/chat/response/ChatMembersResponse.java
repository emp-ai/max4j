package io.bittersweet.max.core.method.chat.response;

import io.bittersweet.max.core.method.BotResponse;
import io.bittersweet.max.core.method.model.user.ChatMemberUser;
import java.util.List;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
public class ChatMembersResponse implements BotResponse {

  /** Chat members list */
  @NotNull
  List<ChatMemberUser> members;

  /** Next available page number */
  @Nullable
  Long marker;
}

package io.bittersweet.max.core.configuration.properties.method.chat.response;

import io.bittersweet.max.core.configuration.properties.method.BotResponse;
import io.bittersweet.max.core.configuration.properties.method.model.chat.Chat;
import java.util.List;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
public class ChatsListResponse implements BotResponse {

  /** Chat list */
  @Nullable
  List<Chat> chats;

  /** Next available page number */
  @Nullable
  Long marker;
}

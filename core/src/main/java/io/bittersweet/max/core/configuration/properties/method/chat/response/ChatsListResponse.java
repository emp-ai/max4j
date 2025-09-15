package io.bittersweet.max.core.configuration.properties.method.chat.response;

import io.bittersweet.max.core.configuration.properties.method.BotResponse;
import java.util.List;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
public class ChatsListResponse implements BotResponse {

  /** Chat list */
  List<Chat> chats;

  /** Next available page number */
  @Nullable
  Long marker;
}

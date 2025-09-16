package io.bittersweet.max.core.method.chat.response;

import io.bittersweet.max.core.method.BotResponse;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
public class PinnedMessageResponse implements BotResponse {

  /** Pinned message, null if no pinned message in chat */
  @Nullable
  String message;
}

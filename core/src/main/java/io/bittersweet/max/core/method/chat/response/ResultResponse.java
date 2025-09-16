package io.bittersweet.max.core.method.chat.response;

import io.bittersweet.max.core.method.BotResponse;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
public class ResultResponse implements BotResponse {

  /** True - if chat was successfully deleted */
  @NotNull
  Boolean success;

  /** Error message, if chat was not deleted */
  @Nullable
  String message;
}

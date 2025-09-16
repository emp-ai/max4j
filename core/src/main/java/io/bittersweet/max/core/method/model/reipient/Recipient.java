package io.bittersweet.max.core.method.model.reipient;

import io.bittersweet.max.core.method.enums.ChatType;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
public class Recipient {

  /** Chat Id */
  @NotNull
  @Pattern(regexp = "\\-?\\d+", message = "Chat id format is wrong")
  Long chatId;

  /** Chat type */
  @NotNull
  ChatType chatType;

  /** User id, if message was sent to user */
  @Nullable
  Long userId;
}

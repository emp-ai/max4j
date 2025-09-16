package io.bittersweet.max.core.method.model.command;

import io.bittersweet.max.core.method.BotResponse;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
public class BotCommand implements BotResponse {

  /** Command name */
  @NotNull
  @Size(max = 64)
  String name;

  /** Command description */
  @Nullable
  @Size(max = 128)
  String description;
}

package io.bittersweet.max.core.configuration.properties.method.bot.response;

import io.bittersweet.max.core.configuration.properties.method.BotResponse;
import jakarta.validation.constraints.Size;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value
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

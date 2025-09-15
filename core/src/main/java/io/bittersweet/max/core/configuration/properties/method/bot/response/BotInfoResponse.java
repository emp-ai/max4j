package io.bittersweet.max.core.configuration.properties.method.bot.response;

import io.bittersweet.max.core.configuration.properties.method.BotResponse;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value
public class BotInfoResponse implements BotResponse {

  /** User ID */
  @NotNull
  Long userId;

  /** User's first name */
  @NotNull
  String firstName;

  /** User's last name */
  @Nullable
  String lastName;

  /** User's name */
  @Nullable
  @Deprecated(forRemoval = true, since = "Use MeResponse.firstName instead")
  String name;

  /** Public user's name */
  @Nullable
  String username;

  /** Is bot or not */
  @NotNull
  Boolean isBot;

  /** Last activity time in millis */
  @NotNull
  Long lastActivityTime;

  /** User's description, max length = 16000 */
  @Nullable
  @Size(max = 16000)
  String description;

  /** User's avatar URL */
  @Nullable
  String avatar_url;

  /** High-res user's avatar URL */
  @Nullable
  String full_avatar_url;

  /** Available bot commands */
  @Nullable
  @Size(max = 32)
  List<BotCommand> commands;
}

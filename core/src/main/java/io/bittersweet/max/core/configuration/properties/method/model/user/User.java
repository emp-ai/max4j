package io.bittersweet.max.core.configuration.properties.method.model.user;

import io.bittersweet.max.core.configuration.properties.method.BotResponse;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@SuperBuilder
public class User implements BotResponse {

  /** User ID */
  @NotNull
  private Long userId;

  /** User's first name */
  @NotNull
  private String firstName;

  /** User's last name */
  @Nullable
  private String lastName;

  /** User's name */
  @Nullable
  @Deprecated(forRemoval = true, since = "Use MeResponse.firstName instead")
  private String name;

  /** Public user's name */
  @Nullable
  private String username;

  /** Is bot or not */
  @NotNull
  private Boolean isBot;

  /** Last activity time in millis */
  @NotNull
  private Long lastActivityTime;
}

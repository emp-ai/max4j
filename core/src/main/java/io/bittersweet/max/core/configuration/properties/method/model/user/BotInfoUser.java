package io.bittersweet.max.core.configuration.properties.method.model.user;

import io.bittersweet.max.core.configuration.properties.method.model.command.BotCommand;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.Nullable;

@Value
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class BotInfoUser extends User {

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

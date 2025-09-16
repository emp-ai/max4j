package io.bittersweet.max.core.configuration.properties.method.model.update;

import io.bittersweet.max.core.configuration.properties.method.enums.UpdateType;
import io.bittersweet.max.core.configuration.properties.method.model.message.Message;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.Nullable;

@Data
@SuperBuilder
public class Update {

  /** Update type */
  @NotNull
  private UpdateType updateType;

  /** Update time */
  @NotNull
  private Long timestamp;

  /** Message data */
  @NotNull
  private Message message;

  /** User language in IETF BCP 47 */
  @Nullable
  private String userLocal;
}

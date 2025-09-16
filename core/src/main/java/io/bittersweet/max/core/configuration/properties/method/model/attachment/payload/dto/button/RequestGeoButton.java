package io.bittersweet.max.core.configuration.properties.method.model.attachment.payload.dto.button;

import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;

@Value
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class RequestGeoButton extends BotButton {

  /** If true, it sends the location without requesting user confirmation. */
  @NotNull
  Boolean quick;
}

package io.bittersweet.max.core.configuration.properties.method.model.attachment.payload.dto.button;

import io.bittersweet.max.core.configuration.properties.method.enums.CallbackButtonIntent;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;

@Value
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class LinkButton extends BotButton {

  @NotNull
  @Size(max = 2048)
  String url;
}

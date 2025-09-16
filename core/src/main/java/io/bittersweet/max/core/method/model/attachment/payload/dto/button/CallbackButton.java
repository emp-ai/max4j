package io.bittersweet.max.core.method.model.attachment.payload.dto.button;

import io.bittersweet.max.core.method.enums.CallbackButtonIntent;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;

@Value
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CallbackButton extends BotButton {

  @NotNull
  @Size(max = 1024)
  String payload;

  @NotNull
  CallbackButtonIntent intent = CallbackButtonIntent.DEFAULT;
}

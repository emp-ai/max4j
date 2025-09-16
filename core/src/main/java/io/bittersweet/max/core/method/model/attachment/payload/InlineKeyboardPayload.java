package io.bittersweet.max.core.method.model.attachment.payload;

import io.bittersweet.max.core.method.model.attachment.payload.dto.button.BotButton;
import java.util.List;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

@Value
@Builder
public class InlineKeyboardPayload implements AttachmentPayload {

  @NotNull
  List<BotButton> buttons;
}

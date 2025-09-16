package io.bittersweet.max.core.method.model.attachment;


import io.bittersweet.max.core.method.model.attachment.payload.InlineKeyboardPayload;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;

@Value
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class InlineKeyboardAttachment extends Attachment {

  @NotNull
  InlineKeyboardPayload payload;

  @NotNull
  String filename;

  @NotNull
  Long size;
}

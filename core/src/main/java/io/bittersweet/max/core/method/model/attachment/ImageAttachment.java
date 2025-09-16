package io.bittersweet.max.core.method.model.attachment;


import io.bittersweet.max.core.method.model.attachment.payload.PhotoAttachmentPayload;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;

@Value
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ImageAttachment extends Attachment {

  @NotNull
  PhotoAttachmentPayload payload;
}

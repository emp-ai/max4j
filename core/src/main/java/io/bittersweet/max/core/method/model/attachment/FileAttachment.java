package io.bittersweet.max.core.method.model.attachment;


import io.bittersweet.max.core.method.model.attachment.payload.PhotoAttachmentPayload;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;

@Value
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class FileAttachment extends Attachment {

  @NotNull
  PhotoAttachmentPayload payload;

  @NotNull
  String filename;

  @NotNull
  Long size;
}

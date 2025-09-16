package io.bittersweet.max.core.method.model.attachment;

import io.bittersweet.max.core.method.model.attachment.payload.ContactAttachmentPayload;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
public class ContactAttachment extends Attachment {

  @NotNull
  ContactAttachmentPayload payload;
}

package io.bittersweet.max.core.configuration.properties.method.model.attachment;

import io.bittersweet.max.core.configuration.properties.method.model.attachment.payload.ContactAttachmentPayload;
import io.bittersweet.max.core.configuration.properties.method.model.attachment.payload.SitckerAttachmentPayload;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
public class ContactAttachment extends Attachment {

  @NotNull
  ContactAttachmentPayload payload;
}

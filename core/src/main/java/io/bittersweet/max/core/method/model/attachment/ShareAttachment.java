package io.bittersweet.max.core.method.model.attachment;

import io.bittersweet.max.core.method.model.attachment.payload.ContactAttachmentPayload;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
public class ShareAttachment extends Attachment {

  @NotNull
  ContactAttachmentPayload payload;

  @Nullable
  String title;

  @Nullable
  String description;

  @Nullable
  String imageUrl;
}

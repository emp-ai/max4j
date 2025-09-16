package io.bittersweet.max.core.method.model.attachment;

import io.bittersweet.max.core.method.model.attachment.payload.SitckerAttachmentPayload;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
public class StickerAttachment extends Attachment {

  @NotNull
  SitckerAttachmentPayload payload;

  @Nullable
  Integer width;

  @Nullable
  Integer height;
}

package io.bittersweet.max.core.method.model.attachment.payload;

import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
public class ShareAttachmentPayload implements AttachmentPayload {

  @Nullable
  String token;

  @Nullable
  String url;
}

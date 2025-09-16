package io.bittersweet.max.core.method.model.attachment.payload;

import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

@Value
@Builder
public class PhotoAttachmentPayload implements AttachmentPayload{

  @NotNull
  Long photoId;

  @NotNull
  String token;

  @NotNull
  String url;
}

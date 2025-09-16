package io.bittersweet.max.core.configuration.properties.method.model.attachment.payload;

import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

@Value
@Builder
public class MediaAttachmentPayload implements AttachmentPayload{

  @NotNull
  String token;

  @NotNull
  String url;
}

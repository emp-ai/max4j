package io.bittersweet.max.core.method.model.attachment.payload;

import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

@Value
@Builder
public class SitckerAttachmentPayload implements AttachmentPayload{

  @NotNull
  String url;

  @NotNull
  String code;
}

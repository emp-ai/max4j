package io.bittersweet.max.core.configuration.properties.method.model.attachment;

import io.bittersweet.max.core.configuration.properties.method.model.attachment.payload.SitckerAttachmentPayload;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
public class LocationAttachment extends Attachment {

  @NotNull
  Double latitude;

  @NotNull
  Double longitude;
}

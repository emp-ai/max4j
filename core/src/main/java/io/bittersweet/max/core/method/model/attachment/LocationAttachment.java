package io.bittersweet.max.core.method.model.attachment;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
public class LocationAttachment extends Attachment {

  @NotNull
  Double latitude;

  @NotNull
  Double longitude;
}

package io.bittersweet.max.core.configuration.properties.method.model.attachment.payload;

import io.bittersweet.max.core.configuration.properties.method.model.user.User;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
public class ContactAttachmentPayload implements AttachmentPayload {

  @Nullable
  String vcfInfo;

  @Nullable
  User maxInfo;
}

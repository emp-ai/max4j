package io.bittersweet.max.core.method.model.attachment.payload;

import io.bittersweet.max.core.method.model.user.User;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
public class ContactAttachmentPayload implements AttachmentPayload {

  @Nullable
  String vcfInfo;

  @Nullable
  User maxInfo;
}

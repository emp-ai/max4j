package io.bittersweet.max.core.method.model.attachment;

import io.bittersweet.max.core.method.model.attachment.payload.MediaAttachmentPayload;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
public class AudioAttachment extends Attachment {

  @NotNull
  MediaAttachmentPayload payload;

  @Nullable
  String transcription;
}

package io.bittersweet.max.core.configuration.properties.method.model.attachment;

import io.bittersweet.max.core.configuration.properties.method.model.attachment.payload.MediaAttachmentPayload;
import io.bittersweet.max.core.configuration.properties.method.model.attachment.payload.dto.VideoThumbnail;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
public class VideoAttachment extends Attachment {

  @NotNull
  MediaAttachmentPayload payload;

  @Nullable
  VideoThumbnail thumbnail;

  @Nullable
  Integer width;

  @Nullable
  Integer height;

  @Nullable
  Integer duration;
}

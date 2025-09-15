package io.bittersweet.max.core.configuration.properties.method.bot.request;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
public class PhotoAttachmentRequestPayload {

  /** Image URL */
  @Nullable
  @Size(min = 1)
  String url;

  /** Existing photos token */
  @Nullable
  String token;

  /** Tokens of photos received after loading */
  @Nullable
  String photos;
}

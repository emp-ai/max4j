package io.bittersweet.max.core.method.model.image;

import java.util.List;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
public class PhotoAttachmentRequestPayload {

  /** Image url */
  @Nullable
  String url;

  /** Existed image token */
  @Nullable
  String token;

  /** Tokens of loaded images */
  @Nullable
  List<String> photos;
}

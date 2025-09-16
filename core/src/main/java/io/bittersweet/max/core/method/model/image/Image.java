package io.bittersweet.max.core.method.model.image;

import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

@Value
@Builder
public class Image {

  /** Image url */
  @NotNull
  String url;
}

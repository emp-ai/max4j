package io.bittersweet.max.core.configuration.properties.method.chat.response;

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

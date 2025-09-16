package io.bittersweet.max.core.configuration.properties.method.model.message;

import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

@Value
@Builder
public class MessageStat {

  /** Message views count */
  @NotNull
  Integer views;
}

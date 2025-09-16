package io.bittersweet.max.core.method.model.update;

import io.bittersweet.max.core.method.model.message.Message;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class MessageUpdateCallback extends Update {

  /** Message */
  @NotNull
  Message message;

  /** User language in IETF BCP 47 */
  @Nullable
  String userLocale;
}

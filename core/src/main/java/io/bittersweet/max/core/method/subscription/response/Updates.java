package io.bittersweet.max.core.method.subscription.response;

import io.bittersweet.max.core.method.BotResponse;
import io.bittersweet.max.core.method.model.update.Update;
import java.util.List;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
public class Updates implements BotResponse {

  /** Updates list */
  @NotNull
  List<Update> updates;

  /** Next page marker */
  @Nullable
  Long marker;
}

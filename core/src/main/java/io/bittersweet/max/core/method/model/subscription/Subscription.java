package io.bittersweet.max.core.method.model.subscription;

import io.bittersweet.max.core.method.enums.UpdateType;
import java.util.List;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

@Value
@Builder
public class Subscription {

  /** Webhook URL */
  @NotNull
  String url;

  /** When URL was registered in millis */
  @NotNull
  Long time;

  /** Updates types bot subscribed on */
  @NotNull
  List<UpdateType> updateTypes;
}

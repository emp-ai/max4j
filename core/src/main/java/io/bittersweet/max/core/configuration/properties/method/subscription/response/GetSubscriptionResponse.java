package io.bittersweet.max.core.configuration.properties.method.subscription.response;

import io.bittersweet.max.core.configuration.properties.method.BotResponse;
import io.bittersweet.max.core.configuration.properties.method.model.subscription.Subscription;
import java.util.List;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

@Value
@Builder
public class GetSubscriptionResponse implements BotResponse {

  /** Subscriptions list */
  @NotNull
  List<Subscription> subscriptions;
}

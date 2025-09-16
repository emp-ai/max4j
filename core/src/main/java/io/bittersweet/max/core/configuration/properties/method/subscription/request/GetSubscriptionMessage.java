package io.bittersweet.max.core.configuration.properties.method.subscription.request;

import io.bittersweet.max.core.configuration.properties.method.BotMethod;
import io.bittersweet.max.core.configuration.properties.method.enums.MethodType;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
public class GetSubscriptionMessage extends BotMethod {

  @Override
  public MethodType getMethod() {
    return MethodType.SUBSCRIPTION_GET;
  }
}

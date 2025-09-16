package io.bittersweet.max.core.method.subscription.request;

import io.bittersweet.max.core.method.BotMethod;
import io.bittersweet.max.core.method.enums.MethodType;
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

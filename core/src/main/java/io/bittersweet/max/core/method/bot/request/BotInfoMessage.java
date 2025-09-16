package io.bittersweet.max.core.method.bot.request;

import io.bittersweet.max.core.method.enums.MethodType;
import io.bittersweet.max.core.method.BotMethod;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class BotInfoMessage extends BotMethod {

  @Override
  public MethodType getMethod() {
    return MethodType.BOT_INFO;
  }
}

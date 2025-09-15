package io.bittersweet.max.core.configuration.properties.method.bot.request;

import static io.bittersweet.max.core.configuration.properties.method.enums.MethodType.BOT_INFO;

import io.bittersweet.max.core.configuration.properties.method.enums.MethodType;
import io.bittersweet.max.core.configuration.properties.method.BotMethod;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class BotInfoMessage extends BotMethod {

  @Override
  public MethodType getMethod() {
    return BOT_INFO;
  }
}

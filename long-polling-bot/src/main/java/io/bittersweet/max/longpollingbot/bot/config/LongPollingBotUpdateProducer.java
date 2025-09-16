package io.bittersweet.max.longpollingbot.bot.config;

import io.bittersweet.max.core.method.model.update.Update;
import io.bittersweet.max.core.method.subscription.response.Updates;

public interface LongPollingBotUpdateProducer {

  void notify(Update update);

  void consumeBatch(Updates updates);
}

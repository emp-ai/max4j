package io.bittersweet.max.longpollingbot.bot.config;

import io.bittersweet.max.core.method.model.update.Update;

public interface LongPollingBotUpdateListener {

  void consume(Update update);
}

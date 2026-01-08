package io.bittersweet.max.longpollingbot;

import io.bittersweet.max.core.method.model.update.Update;

public interface MaxLongPollingConsumer {

  boolean consume(Update update);

}

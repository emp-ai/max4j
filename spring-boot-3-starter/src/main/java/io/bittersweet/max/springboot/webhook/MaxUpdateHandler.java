package io.bittersweet.max.springboot.webhook;

import io.bittersweet.max.core.model.Update;

@FunctionalInterface
public interface MaxUpdateHandler {

  void handle(Update update);
}

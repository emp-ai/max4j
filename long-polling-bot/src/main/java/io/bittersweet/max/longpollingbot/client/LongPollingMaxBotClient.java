package io.bittersweet.max.longpollingbot.client;

import io.bittersweet.max.core.method.subscription.response.Updates;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface LongPollingMaxBotClient {

  String ME = "/me";
  String UPDATES = "/updates";

  @GetMapping(UPDATES)
  Updates poll();

  @GetMapping(ME)
  ResponseEntity<String> ping();

}

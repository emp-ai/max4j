package io.bittersweet.max.longpollingbot.client;

import org.springframework.web.bind.annotation.GetMapping;

public interface MaxBotClient {

  String ME = "/me";

  @GetMapping(ME)
  String getBotInfo();

}

package io.bittersweet.max.longpollingbot.bot.config;

import io.bittersweet.max.longpollingbot.client.MaxBotClient;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class MaxLongPollingBot {

  private final MaxBotClient maxBotClient;
  private final ScheduledExecutorService executor;

  public MaxLongPollingBot(@Autowired MaxBotClient maxBotClient, @Autowired ScheduledExecutorService executor) {
    this.maxBotClient = maxBotClient;
    this.executor = executor;
  }

  @EventListener(ApplicationReadyEvent.class)
  public void startBot() {
    executor.scheduleAtFixedRate(() -> {
      System.out.println(maxBotClient.getBotInfo());
    },1L, 100L, TimeUnit.MILLISECONDS);
  }
}

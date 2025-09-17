package io.bittersweet.max.longpollingbot.bot.config;

import feign.RetryableException;
import io.bittersweet.max.core.method.model.update.Update;
import io.bittersweet.max.core.method.subscription.response.Updates;
import io.bittersweet.max.longpollingbot.client.LongPollingMaxBotClient;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
final class LongPollingBotProducer implements LongPollingBotUpdateProducer {

  private final LongPollingMaxBotClient longPollingMaxBotClient;
  private final ExecutorService consumerExecutor = Executors.newSingleThreadExecutor();
  private final ScheduledExecutorService executor;
  private final List<LongPollingBotUpdateListener> listeners;

  private boolean running = false;
  private int attempt = 0;
  private final int retries = 3;

  @PostConstruct
  private void init() {
    if (listeners.isEmpty()) {
      log.warn("No any MAX bot update listeners registered, bot stopped. (No any LongPollingBotUpdateListener beans are found)");
      return;
    }

    log.info("Registered listeners - {}", listeners.stream()
        .map(l -> l.getClass().getSimpleName())
        .collect(Collectors.joining())
    );

    log.info("Starting MAX long-polling bot...");
    connect();

    if (running) {
      log.info("MAX bot running");
      startBot();
    }

    log.warn("MAX bot stopped");
  }

  @Override
  public void notify(Update update) {
    listeners.forEach(l -> l.consume(update));
  }

  @Override
  public void consumeBatch(Updates updates) {
    log.trace("Received updates batch; size - {}, marker - {}", updates.getUpdates().size(), updates.getMarker());
    updates.getUpdates().forEach(update -> consumerExecutor.execute(() -> notify(update)));
  }

  private void connect() {
    while (!running && attempt <= retries) {
      log.info("Trying to connect to max bot; attempt - {} of {}", attempt, retries);
      try {
        ResponseEntity<String> result = longPollingMaxBotClient.ping();
        running = result.getStatusCode().is2xxSuccessful();
        log.info("MAX bot connected after - {} retries", attempt);
      } catch (RetryableException e) {
        log.error("Error while connection to MAX bot - {}", e.getMessage());
        attempt++;
      }
    }
  }

  private void startBot() {
    executor.scheduleAtFixedRate(() -> {
      try {
        consumeBatch(longPollingMaxBotClient.poll());
      } catch (Exception e) {
        log.info("Max long-poll client exception: ", e);
      }
    }, 1L, 500L, TimeUnit.MILLISECONDS);
  }
}

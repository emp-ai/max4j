package io.bittersweet.max.longpollingbot.configuration;

import io.bittersweet.max.core.client.configuration.MaxBotProperties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ExecutorConfig {

  private final MaxBotProperties properties;

  @Bean
  @ConditionalOnProperty(value = "bittersweet.max.long-polling.enabled")
  public ScheduledExecutorService scheduledExecutorService() {
    return Executors.newScheduledThreadPool(
        properties.getLongPolling().getPoolSize()
    );
  }
}
package io.bittersweet.max.core.client.configuration;

import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.Retryer.Default;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.okhttp.OkHttpClient;
import io.bittersweet.max.core.client.client.MaxBotClient;
import io.bittersweet.max.core.client.configuration.MaxBotProperties.RetryConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;

@Import(FeignClientsConfiguration.class)
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(MaxBotProperties.class)
public class BotClientConfiguration {

  private final MaxBotProperties properties;

  @ConditionalOnProperty(name = "bittersweet.max.client.okhttp.enabled", havingValue = "true")
  @ConditionalOnProperty(name = "bittersweet.max.enabled", havingValue = "true")
  @Bean
  public Client maxOkHttpClient() {
    return new OkHttpClient();
  }


  @ConditionalOnProperty(name = "bittersweet.max.enabled", havingValue = "true")
  @ConditionalOnMissingBean(MaxBotClient.class)
  @Bean
  protected MaxBotClient maxBotClient(
      Decoder decoder,
      Encoder encoder,
      Client client,
      Contract contract
  ) {
    return Feign.builder()
        .requestInterceptor(accessTokenInterceptor())
        .client(client)
        .encoder(encoder)
        .retryer(maxBotRetryer())
        .decoder(decoder)
        .contract(contract)
        .target(MaxBotClient.class, properties.getClientUrl());
  }

  @ConditionalOnProperty(name = "bittersweet.max.enabled", havingValue = "true")
  public Retryer maxBotRetryer() {
    RetryConfig retryConfig = properties.getRetry();
    return retryConfig.isEnabled() ? new Default(
        retryConfig.getInitialDelay(),
        retryConfig.getMaxDelay(),
        retryConfig.getAttempts()
    ) :
        Retryer.NEVER_RETRY;
  }

  private RequestInterceptor accessTokenInterceptor() {
    return (requestTemplate) -> requestTemplate.header(HttpHeaders.AUTHORIZATION, properties.getApiKey());
  }

}

package io.bittersweet.max.longpollingbot.configuration;

import static java.util.concurrent.TimeUnit.SECONDS;

import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.Retryer.Default;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.okhttp.OkHttpClient;
import io.bittersweet.max.core.client.configuration.MaxBotProperties;
import io.bittersweet.max.longpollingbot.client.LongPollingMaxBotClient;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(FeignClientsConfiguration.class)
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(MaxBotProperties.class)
public class MaxLongPollingClientConfiguration {

  private final MaxBotProperties properties;
  private final String ACCESS_TOKEN_PARAM_NAME = "access_token";

  @ConditionalOnMissingBean
  @Bean
  public Client okHttpClient() {
    return new OkHttpClient();
  }

  @ConditionalOnProperty(name = "bittersweet.max.long-polling.enabled", havingValue = "true")
  @ConditionalOnMissingBean(LongPollingMaxBotClient.class)
  @Bean
  protected LongPollingMaxBotClient longPollingMaxBotClient(
      Decoder decoder,
      Encoder encoder,
      Client client,
      Contract contract,
      LongPollingBotErrorDecoder errorDecoder
  ) {
    return Feign.builder()
        .requestInterceptor(accessTokenInterceptor())
        .client(client)
        .encoder(encoder)
        .decoder(decoder)
        .errorDecoder(errorDecoder)
        .contract(contract)
        .retryer(maxBotConnectionRetryer())
        .target(LongPollingMaxBotClient.class, properties.getClientUrl());
  }

  private RequestInterceptor accessTokenInterceptor() {
    return (requestTemplate) -> requestTemplate.query(ACCESS_TOKEN_PARAM_NAME, properties.getApiKey());
  }

  @Bean
  @ConditionalOnProperty(value = "bittersweet.max.long-polling.enabled")
  public Retryer maxBotConnectionRetryer() {
    return new Default(500, SECONDS.toMillis(10), 100);
  }
}


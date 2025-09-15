package io.bittersweet.max.longpollingbot.configuration;

import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import io.bittersweet.max.core.configuration.properties.MaxBotProperties;
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
public class LongPollingBotConfiguration {

  private final MaxBotProperties properties;
  private final String ACCESS_TOKEN_PARAM_NAME = "access_token";

  @ConditionalOnProperty(name = "bittersweet.max.enabled", havingValue = "true")
  @ConditionalOnMissingBean(LongPollingMaxBotClient.class)
  @Bean
  protected LongPollingMaxBotClient bookingPrepayConnectBookingClient(
      Decoder decoder,
      Encoder encoder,
      Client client,
      Contract contract
  ) {
    return Feign.builder()
        .requestInterceptor(accessTokenInterceptor())
        .client(client)
        .encoder(encoder)
        .decoder(decoder)
        .contract(contract)
        .target(LongPollingMaxBotClient.class, properties.getClientUrl());
  }

  private RequestInterceptor accessTokenInterceptor()  {
    return (requestTemplate) -> {
      requestTemplate.query(ACCESS_TOKEN_PARAM_NAME, properties.getApiKey());
    };
  }

}

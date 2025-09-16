package io.bittersweet.max.longpollingbot.configuration;

import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import io.bittersweet.max.core.configuration.properties.client.configuration.MaxBotProperties;
import io.bittersweet.max.longpollingbot.client.MaxBotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(FeignClientsConfiguration.class)
@Configuration
@EnableConfigurationProperties(MaxBotProperties.class)
public class MaxClientConfiguration {

  private final MaxBotProperties properties;
  private final String ACCESS_TOKEN_PARAM_NAME = "access_token";

  public MaxClientConfiguration(@Autowired MaxBotProperties properties) {
    this.properties = properties;
  }

  @ConditionalOnProperty(name = "bittersweet.max.enabled", havingValue = "true")
  @ConditionalOnMissingBean(MaxBotClient.class)
  @Bean
  protected MaxBotClient bookingPrepayConnectBookingClient(
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
        .target(MaxBotClient.class, properties.getClientUrl());
  }

  private RequestInterceptor accessTokenInterceptor()  {
    return (requestTemplate) -> {
      requestTemplate.query(ACCESS_TOKEN_PARAM_NAME, properties.getApiKey());
    };
  }

}

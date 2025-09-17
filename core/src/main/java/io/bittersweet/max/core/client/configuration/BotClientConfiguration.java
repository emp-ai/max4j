package io.bittersweet.max.core.client.configuration;

import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.okhttp.OkHttpClient;
import io.bittersweet.max.core.client.client.MaxBotClient;
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
public class BotClientConfiguration {

  private final MaxBotProperties properties;
  private final String ACCESS_TOKEN_PARAM_NAME = "access_token";

  @ConditionalOnMissingBean
  @Bean
  public Client okHttpClient() {
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
        .retryer(Retryer.NEVER_RETRY)
        .decoder(decoder)
        .contract(contract)
        .target(MaxBotClient.class, properties.getClientUrl());
  }

  private RequestInterceptor accessTokenInterceptor()  {
    return (requestTemplate) -> requestTemplate.query(ACCESS_TOKEN_PARAM_NAME, properties.getApiKey());
  }

}

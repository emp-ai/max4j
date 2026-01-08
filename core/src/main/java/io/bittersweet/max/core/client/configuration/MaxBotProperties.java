package io.bittersweet.max.core.client.configuration;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@ConfigurationProperties("bittersweet.max")
public class MaxBotProperties {

  /** Enable or disable bot */
  @NotNull
  private Boolean enabled = false;

  /** API Key of your MAX bot */
  @NotEmpty
  private String apiKey;

  /** MAX bot official url */
  @NotEmpty
  private String clientUrl = "https://botapi.max.ru";

  @Valid
  @NotNull
  private ClientConfig client;

  @Valid
  @NotNull
  private RetryConfig retry;

  @Getter
  @Setter
  public static class ClientConfig {

    @Valid
    @NotNull
    private Client okhttp;

    @Getter
    @Setter
    public static class Client {

      private boolean enabled = false;

    }
  }

  @Getter
  @Setter
  public static class RetryConfig {

    private boolean enabled = false;

    private long initialDelay = 100;

    private long maxDelay = 1000;

    private int attempts = 3;
  }
}

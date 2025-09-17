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
  private Boolean enabled;

  /** API Key of your MAX bot */
  @NotEmpty
  private String apiKey;

  /** MAX bot official url */
  @NotEmpty
  private String clientUrl = "https://botapi.max.ru";

  /** Configuration for long-polling bot */
  @Valid
  @NotNull
  private BotConfig longPolling;

  @Getter
  @Setter
  public static class BotConfig {

    /** Bot is enabled */
    private boolean enabled = true;

    /** Pool size for executor */
    private int poolSize = 10;
  }
}

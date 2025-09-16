package io.bittersweet.max.core.configuration.properties.client.configuration;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
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

  @NotEmpty
  private String clientUrl;
}

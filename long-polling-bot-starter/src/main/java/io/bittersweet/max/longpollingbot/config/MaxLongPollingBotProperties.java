package io.bittersweet.max.longpollingbot.config;

import io.bittersweet.max.core.method.enums.UpdateType;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Component
@ConfigurationProperties("bittersweet.max.long-poll")
public class MaxLongPollingBotProperties {

  /** Enable or disable long-polling bot */
  @NotNull
  private Boolean enabled = true;

  /** Maximum message count in batch. Default - 100 */
  @Nullable
  @Min(1)
  @Max(1000)
  private Integer limit;

  /** Polling request timeout. Default - 30 */
  @Nullable
  @Min(0)
  @Max(90)
  private Integer timeout;

  /**
   * If passed, the bot will receive updates that have not yet been received.
   * If it is not transmitted, it will receive all new updates.
   */
  @Nullable
  private Long marker;

  /** A list of the types of updates that the bot wants to receive */
  @Nullable
  private List<UpdateType> types;

}

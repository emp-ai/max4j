package io.bittersweet.max.core.configuration.properties.method.subscription.request;

import io.bittersweet.max.core.configuration.properties.method.BotMethod;
import io.bittersweet.max.core.configuration.properties.method.enums.MethodType;
import io.bittersweet.max.core.configuration.properties.method.enums.UpdateType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

/**
 * This method can be used to get updates if your bot is not subscribed to the WebHook. The method uses long polling.
 * <p>
 * Each update has its own sequence number. The marker property in the response indicates the next expected update.
 * <p>
 * All previous updates are considered completed after passing the marker parameter. If the marker parameter is not passed, the bot will
 * receive all updates that have occurred since the last confirmation.
 */
@Value
@Builder
@EqualsAndHashCode(callSuper = true)
public class PollMessage extends BotMethod {

  /**
   * By default: 100
   * <p>
   * The maximum number of updates to receive
   */
  @Nullable
  @Min(1)
  @Max(1000)
  Integer limit;

  /**
   * Default: 30
   * <p>
   * Timeout in seconds for long polling
   */
  @Nullable
  @Min(1)
  @Max(90)
  Integer timeout;

  /**
   * If passed, the bot will receive updates that have not yet been received. If not, it will receive all new updates.
   */
  @Nullable
  Long marker;

  /** Updates type which need to poll */
  @Nullable
  List<UpdateType> updateTypes;

  @Override
  public MethodType getMethod() {
    return MethodType.POLL_UPDATES;
  }
}

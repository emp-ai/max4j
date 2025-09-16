package io.bittersweet.max.core.configuration.properties.method.chat.request;

import io.bittersweet.max.core.configuration.properties.method.BotMethod;
import io.bittersweet.max.core.configuration.properties.method.enums.MethodType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
public class CharMembersMessage extends BotMethod {

  @NotNull
  @Pattern(regexp = "\\-?\\d+", message = "Chat id format is wrong")
  Long chatId;

  /** A list of user IDs whose membership you need to obtain. When this parameter is passed, the count and marker parameters are ignored. */
  @Nullable
  List<Long> userId;

  /** Result count per page; default - 20 */
  @Nullable
  @Min(1)
  @Max(100)
  Integer count;

  /** Page number. If you need a first page - pass null */
  @Nullable
  Long marker;

  @Override
  public MethodType getMethod() {
    return MethodType.GET_CHAT_MEMBERS;
  }
}

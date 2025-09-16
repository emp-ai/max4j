package io.bittersweet.max.core.method.chat.request;

import io.bittersweet.max.core.method.BotMethod;
import io.bittersweet.max.core.method.enums.MethodType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
public class ChatsListMessage extends BotMethod {

  /** Result count per page; default - 50 */
  @Nullable
  @Min(1)
  @Max(100)
  Integer count;

  /** Page number. If you need a first page - pass null */
  @Nullable
  Long marker;

  @Override
  public MethodType getMethod() {
    return MethodType.CHATS_LIST;
  }
}

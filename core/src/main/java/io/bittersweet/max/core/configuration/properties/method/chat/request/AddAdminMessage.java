package io.bittersweet.max.core.configuration.properties.method.chat.request;

import io.bittersweet.max.core.configuration.properties.method.BotMethod;
import io.bittersweet.max.core.configuration.properties.method.enums.MethodType;
import io.bittersweet.max.core.configuration.properties.method.model.user.ChatAdmin;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
public class AddAdminMessage extends BotMethod {

  @NotNull
  @Pattern(regexp = "\\-?\\d+", message = "Chat id format is wrong")
  Long chatId;

  /** Admins array to be added */
  @NotNull
  List<ChatAdmin> admins;

  /** Page number. If you need a first page - pass null */
  @Nullable
  Long marker;

  @Override
  public MethodType getMethod() {
    return MethodType.ADD_ADMINS;
  }

}

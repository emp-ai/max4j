package io.bittersweet.max.core.method.bot.request;

import io.bittersweet.max.core.method.BotMethod;
import io.bittersweet.max.core.method.model.command.BotCommand;
import io.bittersweet.max.core.method.enums.MethodType;
import io.bittersweet.max.core.method.model.image.PhotoAttachmentRequestPayload;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
public class EditBotMessage extends BotMethod {

  /** User's first name */
  @Nullable
  String firstName;

  /** User's last name */
  @Nullable
  String lastName;

  /** User's name */
  @Nullable
  @Deprecated(forRemoval = true, since = "Use MeResponse.firstName instead")
  String name;

  /** User's description, max length = 16000 */
  @Nullable
  @Size(min = 1, max = 16000)
  String description;

  /** Available bot commands; Send empty list is wants to remove commands */
  @Nullable
  @Size(max = 32)
  List<BotCommand> commands;

  /** Request to load bot photo */
  @Nullable
  PhotoAttachmentRequestPayload photo;

  @Override
  public MethodType getMethod() {
    return MethodType.BOT_INFO_EDIT;
  }
}

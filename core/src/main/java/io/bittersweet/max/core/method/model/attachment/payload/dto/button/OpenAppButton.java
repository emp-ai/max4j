package io.bittersweet.max.core.method.model.attachment.payload.dto.button;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.Nullable;

@Value
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class OpenAppButton extends BotButton {

  /** The public name (username) of the bot or a link to it, whose mini-application needs to be launched */
  @Nullable
  String webApp;

  /** The ID of the bot whose mini-app you want to launch */
  @Nullable
  Long contactId;

  /** The startup parameter that will be passed to the initData of the mini-application */
  @Nullable
  String payload;
}

package io.bittersweet.max.core.method.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Locale;

public enum UpdateType {

  MESSAGE_CREATED,
  MESSAGE_CALLBACK,
  MESSAGE_EDITED,
  MESSAGE_REMOVED,
  BOT_ADDED,
  BOT_REMOVED,
  DIALOG_MUTED,
  DIALOG_UNMUTED,
  DIALOG_CLEARED,
  DIALOG_REMOVED,
  USER_ADDED,
  USER_REMOVED,
  BOT_STARTER,
  BOT_STOPPED,
  CHAT_TITLE_CHANGED,
  MESSAGE_CHAT_CREATED;

  @JsonCreator
  public static UpdateType fromValue(String value) {
    if (value == null) {
      return null;
    }
    return UpdateType.valueOf(value.toUpperCase(Locale.ROOT));
  }

  @JsonValue
  public String toValue() {
    return name().toLowerCase(Locale.ROOT);
  }
}

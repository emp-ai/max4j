package io.bittersweet.max.core.configuration.properties.method.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;

@Getter
@RequiredArgsConstructor
public enum MethodType {

  /** Get bot overall info */
  BOT_INFO("/me", HttpMethod.GET),

  /** Edit bot */
  BOT_INFO_EDIT("/me", HttpMethod.PATCH),

  /** Get all chats list */
  CHATS_LIST("/chats", HttpMethod.GET),

  /** Get chat by link */
  CHAT_BY_LINK("/chats/{chatLink}", HttpMethod.GET),

  /** Get chat by id */
  CHAT_BY_ID("/chats/{chatId}", HttpMethod.GET);

  private final String url;
  private final HttpMethod method;
}

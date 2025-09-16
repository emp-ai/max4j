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
  CHAT_BY_ID("/chats/{chatId}", HttpMethod.GET),

  /** Edit chat by id */
  CHAT_EDIT_BY_ID("/chats/{chatId}", HttpMethod.PATCH),

  /** Delete chat by id */
  CHAT_DELETE_BY_ID("/chats/{chatId}", HttpMethod.DELETE),

  /** Send bot actions to chat, such "typing..." */
  CHAT_ACTION("/chats/{chatId}/actions", HttpMethod.POST),

  /** Get chat pinned message */
  PINNED_MESSAGE_GET("/chats/{chatId}/pin", HttpMethod.GET),

  /** Pin message in chat */
  PIN_MESSAGE("/chats/{chatId}/pin", HttpMethod.PUT),

  /** Pin message in chat */
  PIN_MESSAGE_DELETE("/chats/{chatId}/pin", HttpMethod.DELETE),

  /** Pin message in chat */
  CHAT_MEMBER("/chats/{chatId}/members/me", HttpMethod.GET),

  /** Delete user from chat */
  BOT_DELETE("/chats/{chatId}/members/me", HttpMethod.DELETE),

  /** Get all chat admins */
  CHAT_MEMBER_ADMIN_LIST("/chats/{chatId}/members/admins", HttpMethod.GET),

  /** Get all chat admins */
  ADD_ADMINS("/chats/{chatId}/members/admins", HttpMethod.POST),

  /** Discard admin right */
  DISCARD_ADMIN_RIGHT("/chats/{chatId}/members/admins/{userId}", HttpMethod.DELETE),

  /** Get chat members */
  GET_CHAT_MEMBERS("/chats/{chatId}/members", HttpMethod.GET),

  /** Add chat members */
  ADD_CHAT_MEMBERS("/chats/{chatId}/members", HttpMethod.POST),

  /** Delete chat members */
  DELETE_CHAT_MEMBERS("/chats/{chatId}/members", HttpMethod.DELETE),

  /** Get webhook subscription list */
  SUBSCRIPTION_GET("/subscriptions", HttpMethod.GET),

  /** Add URL to webhook subscription */
  SUBSCRIPTION_ADD("/subscriptions", HttpMethod.POST),

  /** Remove URL from subscription */
  SUBSCRIPTION_DELETE("/subscriptions", HttpMethod.DELETE),

  /** Poll messages if using long-polling */
  POLL_UPDATES("/updates", HttpMethod.GET);

  private final String url;
  private final HttpMethod method;
}

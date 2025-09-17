package io.bittersweet.max.core.client.client;

import io.bittersweet.max.core.method.bot.request.EditBotMessage;
import io.bittersweet.max.core.method.chat.request.AddAdminMessage;
import io.bittersweet.max.core.method.chat.request.AddChatMembersMessage;
import io.bittersweet.max.core.method.chat.request.CharMembersMessage;
import io.bittersweet.max.core.method.chat.request.ChatAdminsMessage;
import io.bittersweet.max.core.method.chat.request.DeleteChatMemberMessage;
import io.bittersweet.max.core.method.chat.request.DiscardAdminMessage;
import io.bittersweet.max.core.method.chat.response.ChatAdminsResponse;
import io.bittersweet.max.core.method.chat.response.ChatMembersResponse;
import io.bittersweet.max.core.method.chat.request.ChatsListMessage;
import io.bittersweet.max.core.method.chat.request.EditChatMessage;
import io.bittersweet.max.core.method.chat.request.PinMessage;
import io.bittersweet.max.core.method.model.chat.Chat;
import io.bittersweet.max.core.method.chat.response.ChatsListResponse;
import io.bittersweet.max.core.method.chat.response.ResultResponse;
import io.bittersweet.max.core.method.chat.response.PinnedMessageResponse;
import io.bittersweet.max.core.method.model.user.BotInfoUser;
import io.bittersweet.max.core.method.model.user.ChatMemberUser;
import io.bittersweet.max.core.method.subscription.request.AddSubscriptionMessage;
import io.bittersweet.max.core.method.subscription.request.DeleteSubscriptionMessage;
import io.bittersweet.max.core.method.subscription.request.PollMessage;
import io.bittersweet.max.core.method.subscription.response.GetSubscriptionResponse;
import io.bittersweet.max.core.method.subscription.response.Updates;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface MaxBotClient {

  String ME = "/me";
  String CHATS = "/chats";
  String CHATS_BY_LINK = CHATS + "/{chatLink}";
  String CHATS_BY_ID = CHATS + "/{chatId}";
  String CHAT_ACTION = CHATS_BY_ID + "/actions";
  String PINNED_MESSAGE = CHATS_BY_ID + "/pin";
  String CHAT_MEMBER_INFO = CHATS_BY_ID + "/members/me";
  String CHAT_ADMINS = CHATS_BY_ID + "/members/admins";
  String DISCARD_ADMIN_RIGHT = CHATS_BY_ID + "/members/admins/{userId}";
  String CHAT_MEMBERS = CHATS_BY_ID + "/members";
  String SUBSCRIPTION = "/subscriptions";
  String POLL = "/updates";

  @GetMapping(ME)
  BotInfoUser getBotInfo();

  @PatchMapping(ME)
  BotInfoUser getBotInfo(@RequestBody @Valid EditBotMessage body);

  @GetMapping(CHATS)
  ChatsListResponse getChats(@SpringQueryMap @Valid ChatsListMessage params);

  @GetMapping(CHATS_BY_LINK)
  Chat getChatByLink(@PathVariable @Pattern(regexp = "@?[a-zA-Z]+[a-zA-Z0-9-_]*", message = "Invalid chat link") String chatLink);

  @GetMapping(CHATS_BY_ID)
  Chat getChatById(@PathVariable @Pattern(regexp = "-?\\d+", message = "Chat id format is wrong") Long chatId);

  @PatchMapping(CHATS_BY_ID)
  Chat editChatById(
      @PathVariable @Pattern(regexp = "-?\\d+", message = "Chat id format is wrong") Long chatId,
      @RequestBody @Valid EditChatMessage message
  );

  @DeleteMapping(CHATS_BY_ID)
  ResultResponse deleteChatById(@PathVariable @Pattern(regexp = "-?\\d+", message = "Chat id format is wrong") Long chatId);

  @PostMapping(CHAT_ACTION)
  ResultResponse chatAction(@PathVariable @Pattern(regexp = "-?\\d+", message = "Chat id format is wrong") Long chatId);

  @PostMapping(PINNED_MESSAGE)
  PinnedMessageResponse getPinnedMessage(@PathVariable @Pattern(regexp = "-?\\d+", message = "Chat id format is wrong") Long chatId);

  @PostMapping(PINNED_MESSAGE)
  ResultResponse pinMessage(
      @PathVariable @Pattern(regexp = "-?\\d+", message = "Chat id format is wrong") Long chatId,
      @RequestBody @Valid PinMessage message
  );

  @DeleteMapping(PINNED_MESSAGE)
  ResultResponse deletePinMessage(
      @PathVariable @Pattern(regexp = "-?\\d+", message = "Chat id format is wrong") Long chatId
  );

  @GetMapping(CHAT_MEMBER_INFO)
  ChatMemberUser getChatMemberInfo(@PathVariable @Pattern(regexp = "-?\\d+", message = "Chat id format is wrong") Long chatId);

  @DeleteMapping(CHAT_MEMBER_INFO)
  ResultResponse deleteChatMember(@PathVariable @Pattern(regexp = "-?\\d+", message = "Chat id format is wrong") Long chatId);

  @GetMapping(CHAT_ADMINS)
  ChatAdminsResponse getChatAdmins(
      @PathVariable @Pattern(regexp = "-?\\d+", message = "Chat id format is wrong") Long chatId,
      @RequestBody @Valid ChatAdminsMessage message
  );

  @PostMapping(CHAT_ADMINS)
  ResultResponse addAdmins(
      @PathVariable @Pattern(regexp = "-?\\d+", message = "Chat id format is wrong") Long chatId,
      @RequestBody @Valid AddAdminMessage message
  );

  @DeleteMapping(DISCARD_ADMIN_RIGHT)
  ResultResponse discardAdmin(@SpringQueryMap @Valid DiscardAdminMessage message);

  @GetMapping(CHAT_MEMBERS)
  ChatMembersResponse getChatMembers(@SpringQueryMap @Valid CharMembersMessage message);

  @PostMapping(CHAT_MEMBERS)
  ResultResponse addChatMembers(
      @PathVariable @Pattern(regexp = "-?\\d+", message = "Chat id format is wrong") Long chatId,
      @RequestBody @Valid AddChatMembersMessage message
  );

  @DeleteMapping(CHAT_MEMBERS)
  ResultResponse deleteChatMembers(
      @SpringQueryMap @Valid DeleteChatMemberMessage message
  );

  @GetMapping(SUBSCRIPTION)
  GetSubscriptionResponse getSubscriptions();

  @PostMapping(SUBSCRIPTION)
  ResultResponse addSubscription(@RequestBody @Valid AddSubscriptionMessage message);

  @DeleteMapping(SUBSCRIPTION)
  ResultResponse deleteSubscription(@SpringQueryMap @Valid DeleteSubscriptionMessage message);

  @GetMapping(POLL)
  Updates pollUpdates(@SpringQueryMap @Valid PollMessage message);
}

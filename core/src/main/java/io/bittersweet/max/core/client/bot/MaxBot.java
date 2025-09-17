package io.bittersweet.max.core.client.bot;

import io.bittersweet.max.core.client.client.MaxBotClient;
import io.bittersweet.max.core.method.BotMethod;
import io.bittersweet.max.core.method.BotResponse;
import io.bittersweet.max.core.method.bot.request.EditBotMessage;
import io.bittersweet.max.core.method.chat.request.AddAdminMessage;
import io.bittersweet.max.core.method.chat.request.AddChatMembersMessage;
import io.bittersweet.max.core.method.chat.request.CharMembersMessage;
import io.bittersweet.max.core.method.chat.request.ChatActionMessage;
import io.bittersweet.max.core.method.chat.request.ChatAdminsMessage;
import io.bittersweet.max.core.method.chat.request.ChatByIdMessage;
import io.bittersweet.max.core.method.chat.request.ChatByLinkMessage;
import io.bittersweet.max.core.method.chat.request.ChatMemberMessage;
import io.bittersweet.max.core.method.chat.request.ChatsListMessage;
import io.bittersweet.max.core.method.chat.request.DeleteBotMemberMessage;
import io.bittersweet.max.core.method.chat.request.DeleteChatMemberMessage;
import io.bittersweet.max.core.method.chat.request.DeleteChatMessage;
import io.bittersweet.max.core.method.chat.request.DeletePinMessage;
import io.bittersweet.max.core.method.chat.request.DiscardAdminMessage;
import io.bittersweet.max.core.method.chat.request.EditChatMessage;
import io.bittersweet.max.core.method.chat.request.PinMessage;
import io.bittersweet.max.core.method.chat.request.PinnedMessage;
import io.bittersweet.max.core.method.subscription.request.AddSubscriptionMessage;
import io.bittersweet.max.core.method.subscription.request.DeleteSubscriptionMessage;
import io.bittersweet.max.core.method.subscription.request.PollMessage;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "bittersweet.max.enabled", havingValue = "true")
public final class MaxBot {

  private final MaxBotClient client;

  public <T extends BotResponse> T execute(@NotNull BotMethod method) {
    return methodProvider(method);
  }

  private  <T extends BotResponse> T methodProvider(@NotNull BotMethod method) {
    BotResponse response = switch (method.getMethod()) {
      case BOT_INFO -> client.getBotInfo();
      case BOT_INFO_EDIT -> client.getBotInfo((EditBotMessage) method);
      case CHATS_LIST -> client.getChats((ChatsListMessage) method);
      case CHAT_BY_LINK -> {
        var param = (ChatByLinkMessage) method;
        yield client.getChatByLink(param.getChatLink());
      }
      case CHAT_BY_ID -> {
        var param = (ChatByIdMessage) method;
        yield client.getChatById(param.getChatId());
      }
      case CHAT_EDIT_BY_ID -> {
        var param = (EditChatMessage) method;
        yield client.editChatById(param.getChatId(), param);
      }
      case CHAT_DELETE_BY_ID -> {
        var param = (DeleteChatMessage) method;
        yield client.deleteChatById(param.getChatId());
      }
      case CHAT_ACTION -> {
        var param = (ChatActionMessage) method;
        yield client.chatAction(param.getChatId());
      }
      case PINNED_MESSAGE_GET -> {
        var param = (PinnedMessage) method;
        yield client.getPinnedMessage(param.getChatId());
      }
      case PIN_MESSAGE -> {
        var param = (PinMessage) method;
        yield client.pinMessage(param.getChatId(), param);
      }
      case PIN_MESSAGE_DELETE -> {
        var param = (DeletePinMessage) method;
        yield client.deletePinMessage(param.getChatId());
      }
      case CHAT_MEMBER -> {
        var param = (ChatMemberMessage) method;
        yield client.getChatMemberInfo(param.getChatId());
      }
      case BOT_DELETE -> {
        var param = (DeleteBotMemberMessage) method;
        yield client.deleteChatMember(param.getChatId());
      }
      case CHAT_MEMBER_ADMIN_LIST -> {
        var param = (ChatAdminsMessage) method;
        yield client.getChatAdmins(param.getChatId(), param);
      }
      case ADD_ADMINS -> {
        var param = (AddAdminMessage) method;
        yield client.addAdmins(param.getChatId(), param);
      }
      case DISCARD_ADMIN_RIGHT -> {
        var param = (DiscardAdminMessage) method;
        yield client.discardAdmin(param);
      }
      case GET_CHAT_MEMBERS -> {
        var param = (CharMembersMessage) method;
        yield client.getChatMembers(param);
      }
      case ADD_CHAT_MEMBERS -> {
        var param = (AddChatMembersMessage) method;
        yield client.addChatMembers(param.getChatId(), param);
      }
      case DELETE_CHAT_MEMBERS -> {
        var param = (DeleteChatMemberMessage) method;
        yield client.deleteChatMembers(param);
      }
      case SUBSCRIPTION_GET -> client.getSubscriptions();
      case SUBSCRIPTION_ADD -> {
        var param = (AddSubscriptionMessage) method;
        yield client.addSubscription(param);
      }
      case SUBSCRIPTION_DELETE -> {
        var param = (DeleteSubscriptionMessage) method;
        yield client.deleteSubscription(param);
      }
      case POLL_UPDATES -> {
        var param = (PollMessage) method;
        yield client.pollUpdates(param);
      }
    };
    return (T) response;
  }
}

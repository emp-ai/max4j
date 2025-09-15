package io.bittersweet.max.longpollingbot.bot;

import io.bittersweet.max.core.configuration.properties.method.BotMethod;
import io.bittersweet.max.core.configuration.properties.method.BotResponse;
import io.bittersweet.max.core.configuration.properties.method.bot.request.EditBotMessage;
import io.bittersweet.max.core.configuration.properties.method.chat.request.ChatByLinkMessage;
import io.bittersweet.max.core.configuration.properties.method.chat.request.ChatsListMessage;
import io.bittersweet.max.longpollingbot.client.LongPollingMaxBotClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LongPollingMaxBot {

  private final LongPollingMaxBotClient client;

  public <T extends BotMethod> BotResponse execute(T method) {
    return switch (method.getMethod()) {
      case BOT_INFO -> client.getBotInfo();
      case BOT_INFO_EDIT -> client.getBotInfo((EditBotMessage) method);
      case CHATS_LIST -> client.getChats((ChatsListMessage) method);
      case CHAT_BY_LINK -> {
        var param = (ChatByLinkMessage) method;
        yield client.getChatByLink(param.getChatLink());
      }
      case CHAT_BY_ID ->
    };
  }
}

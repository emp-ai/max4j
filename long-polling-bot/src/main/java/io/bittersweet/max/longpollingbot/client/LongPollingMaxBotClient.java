package io.bittersweet.max.longpollingbot.client;

import io.bittersweet.max.core.configuration.properties.method.bot.request.EditBotMessage;
import io.bittersweet.max.core.configuration.properties.method.bot.response.BotInfoResponse;
import io.bittersweet.max.core.configuration.properties.method.chat.request.ChatsListMessage;
import io.bittersweet.max.core.configuration.properties.method.chat.response.Chat;
import io.bittersweet.max.core.configuration.properties.method.chat.response.ChatsListResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface LongPollingMaxBotClient {

  String ME = "/me";

  String CHATS = "/chats";
  String CHATS_BY_LINK = CHATS + "/{chatLink}";
  String CHATS_BY_ID = CHATS + "/{chatId}";

  @GetMapping(ME)
  BotInfoResponse getBotInfo();

  @PatchMapping(ME)
  BotInfoResponse getBotInfo(@RequestBody @Valid EditBotMessage body);

  @GetMapping(CHATS)
  ChatsListResponse getChats(@ModelAttribute @Valid ChatsListMessage params);

  @GetMapping(CHATS_BY_LINK)
  Chat getChatByLink(@PathVariable @Pattern(regexp = "@?[a-zA-Z]+[a-zA-Z0-9-_]*", message = "Invalid chat link") String chatLink);

  @GetMapping(CHATS_BY_ID)
  Chat getChatById(@PathVariable @Pattern(regexp = "\\-?\\d+", message = "Chat id format is wrong") String chatId);
}

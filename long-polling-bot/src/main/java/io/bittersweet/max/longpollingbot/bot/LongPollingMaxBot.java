package io.bittersweet.max.longpollingbot.bot;

import io.bittersweet.max.longpollingbot.client.LongPollingMaxBotClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LongPollingMaxBot {

  private final LongPollingMaxBotClient client;


}

package io.bittersweet.max.springboot.webhook;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.bittersweet.max.core.model.Update;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

class MaxWebhookControllerTest {

  @Test
  void dispatchesUpdateToEveryHandler() {
    MaxUpdateHandler first = mock(MaxUpdateHandler.class);
    MaxUpdateHandler second = mock(MaxUpdateHandler.class);
    Update update = new Update().updateType("message_created").timestamp(1L);
    MaxWebhookController controller = new MaxWebhookController(List.of(first, second), null);

    controller.receive(null, update);

    verify(first).handle(update);
    verify(second).handle(update);
  }

  @Test
  void rejectsInvalidConfiguredSecret() {
    MaxUpdateHandler handler = mock(MaxUpdateHandler.class);
    Update update = new Update().updateType("message_created").timestamp(1L);
    MaxWebhookController controller = new MaxWebhookController(List.of(handler), "expected-secret");

    assertThrows(ResponseStatusException.class, () -> controller.receive("wrong-secret", update));

    verify(handler, never()).handle(update);
  }
}

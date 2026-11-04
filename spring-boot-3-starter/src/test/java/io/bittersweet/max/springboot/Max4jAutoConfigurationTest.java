package io.bittersweet.max.springboot;

import static org.assertj.core.api.Assertions.assertThat;

import io.bittersweet.max.core.MaxClient;
import io.bittersweet.max.core.api.BotsApi;
import io.bittersweet.max.core.api.ChatsApi;
import io.bittersweet.max.core.api.CommentsApi;
import io.bittersweet.max.core.api.MessagesApi;
import io.bittersweet.max.core.api.SubscriptionsApi;
import io.bittersweet.max.core.api.UploadApi;
import io.bittersweet.max.core.client.generated.ApiClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

class Max4jAutoConfigurationTest {

  private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
      .withConfiguration(AutoConfigurations.of(Max4jAutoConfiguration.class));

  @Test
  void staysDisabledByDefault() {
    contextRunner.run(context -> assertThat(context).doesNotHaveBean(MaxClient.class));
  }

  @Test
  void createsClientAndAllApiGroups() {
    contextRunner
        .withPropertyValues("max4j.enabled=true", "max4j.token=test-token")
        .run(context -> {
          assertThat(context).hasSingleBean(MaxClient.class);
          assertThat(context).hasSingleBean(ApiClient.class);
          assertThat(context).hasSingleBean(BotsApi.class);
          assertThat(context).hasSingleBean(ChatsApi.class);
          assertThat(context).hasSingleBean(CommentsApi.class);
          assertThat(context).hasSingleBean(MessagesApi.class);
          assertThat(context).hasSingleBean(SubscriptionsApi.class);
          assertThat(context).hasSingleBean(UploadApi.class);
        });
  }

  @Test
  void failsFastWhenEnabledWithoutToken() {
    contextRunner
        .withPropertyValues("max4j.enabled=true")
        .run(context -> assertThat(context).hasFailed());
  }
}

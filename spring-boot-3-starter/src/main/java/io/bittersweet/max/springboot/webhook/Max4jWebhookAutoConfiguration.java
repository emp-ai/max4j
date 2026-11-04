package io.bittersweet.max.springboot.webhook;

import io.bittersweet.max.springboot.Max4jProperties;
import java.util.List;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass(RestController.class)
@ConditionalOnProperty(prefix = "max4j.webhook", name = "enabled", havingValue = "true")
@ConditionalOnBean(MaxUpdateHandler.class)
public class Max4jWebhookAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  MaxWebhookController maxWebhookController(
      List<MaxUpdateHandler> handlers,
      Max4jProperties properties) {
    return new MaxWebhookController(handlers, properties.getWebhook().getSecret());
  }
}

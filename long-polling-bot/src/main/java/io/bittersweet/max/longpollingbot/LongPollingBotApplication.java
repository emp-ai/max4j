package io.bittersweet.max.longpollingbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class LongPollingBotApplication {

  public static void main(String[] args) {
    SpringApplication.run(LongPollingBotApplication.class, args);
  }

}

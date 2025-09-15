package io.bittersweet.max.core.configuration.properties.method.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;

@Getter
@RequiredArgsConstructor
public enum MethodType {

  BOT_INFO("/me", HttpMethod.GET),

  BOT_INFO_EDIT("/me", HttpMethod.PATCH);

  private final String url;
  private final HttpMethod method;
}

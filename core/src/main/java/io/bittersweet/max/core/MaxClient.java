package io.bittersweet.max.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.bittersweet.max.core.api.BotsApi;
import io.bittersweet.max.core.api.ChatsApi;
import io.bittersweet.max.core.api.CommentsApi;
import io.bittersweet.max.core.api.MessagesApi;
import io.bittersweet.max.core.api.SubscriptionsApi;
import io.bittersweet.max.core.api.UploadApi;
import io.bittersweet.max.core.client.generated.ApiClient;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Entry point for the MAX Bot API.
 *
 * <p>The generated API classes capture their HTTP configuration in their constructors, so the
 * builder configures the base URI, token and timeouts before any API object is created.</p>
 */
public final class MaxClient {

  public static final String DEFAULT_BASE_URL = "https://platform-api2.max.ru";

  private final ApiClient apiClient;
  private BotsApi bots;
  private ChatsApi chats;
  private CommentsApi comments;
  private MessagesApi messages;
  private SubscriptionsApi subscriptions;

  private UploadApi uploads;
  private MaxClient(ApiClient apiClient) {
    this.apiClient = Objects.requireNonNull(apiClient, "apiClient");
  }

  public static MaxClient create(String accessToken) {
    return builder(accessToken).build();
  }

  public static Builder builder(String accessToken) {
    return new Builder(accessToken);
  }

  public ApiClient apiClient() {
    return apiClient;
  }

  public synchronized BotsApi bots() {
    if (bots == null) {
      bots = new BotsApi(apiClient);
    }
    return bots;
  }

  public synchronized ChatsApi chats() {
    if (chats == null) {
      chats = new ChatsApi(apiClient);
    }
    return chats;
  }

  public synchronized CommentsApi comments() {
    if (comments == null) {
      comments = new CommentsApi(apiClient);
    }
    return comments;
  }

  public synchronized MessagesApi messages() {
    if (messages == null) {
      messages = new MessagesApi(apiClient);
    }
    return messages;
  }

  public synchronized SubscriptionsApi subscriptions() {
    if (subscriptions == null) {
      subscriptions = new SubscriptionsApi(apiClient);
    }
    return subscriptions;
  }

  public synchronized UploadApi uploads() {
    if (uploads == null) {
      uploads = new UploadApi(apiClient);
    }
    return uploads;
  }

  public static final class Builder {

    private final String accessToken;
    private String baseUrl = DEFAULT_BASE_URL;
    private Duration connectTimeout = Duration.ofSeconds(10);
    private Duration readTimeout = Duration.ofSeconds(30);
    private ObjectMapper objectMapper;
    private Consumer<HttpRequest.Builder> requestCustomizer;

    private Builder(String accessToken) {
      if (accessToken == null || accessToken.isBlank()) {
        throw new IllegalArgumentException("MAX access token must not be blank");
      }
      this.accessToken = accessToken;
    }

    public Builder baseUrl(String baseUrl) {
      if (baseUrl == null || baseUrl.isBlank()) {
        throw new IllegalArgumentException("MAX API base URL must not be blank");
      }
      this.baseUrl = stripTrailingSlash(baseUrl);
      return this;
    }

    public Builder connectTimeout(Duration connectTimeout) {
      this.connectTimeout = requirePositive(connectTimeout, "connectTimeout");
      return this;
    }

    public Builder readTimeout(Duration readTimeout) {
      this.readTimeout = requirePositive(readTimeout, "readTimeout");
      return this;
    }

    public Builder objectMapper(ObjectMapper objectMapper) {
      this.objectMapper = Objects.requireNonNull(objectMapper, "objectMapper");
      return this;
    }

    public Builder requestCustomizer(Consumer<HttpRequest.Builder> requestCustomizer) {
      this.requestCustomizer = Objects.requireNonNull(requestCustomizer, "requestCustomizer");
      return this;
    }

    public MaxClient build() {
      ApiClient apiClient = new ApiClient();
      apiClient.updateBaseUri(baseUrl);
      apiClient.setConnectTimeout(connectTimeout);
      apiClient.setReadTimeout(readTimeout);
      if (objectMapper != null) {
        apiClient.setObjectMapper(objectMapper);
      }
      apiClient.setRequestInterceptor(request -> {
        request.setHeader("Authorization", accessToken);
        if (requestCustomizer != null) {
          requestCustomizer.accept(request);
        }
      });
      return new MaxClient(apiClient);
    }

    private static Duration requirePositive(Duration value, String name) {
      Objects.requireNonNull(value, name);
      if (value.isZero() || value.isNegative()) {
        throw new IllegalArgumentException(name + " must be positive");
      }
      return value;
    }

    private static String stripTrailingSlash(String value) {
      String result = value;
      while (result.endsWith("/")) {
        result = result.substring(0, result.length() - 1);
      }
      return result;
    }
  }
}

package io.bittersweet.max.core.exception;

import feign.Request;
import feign.Request.HttpMethod;
import feign.RetryableException;
import java.util.Date;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MaxBotRetryableException extends RetryableException {

  public MaxBotRetryableException(
      int status,
      @NotNull String message,
      @NotNull HttpMethod httpMethod,
      @Nullable Throwable cause,
      @Nullable Date retryAfter,
      @NotNull Request request
  ) {
    super(status, message, httpMethod, cause, retryAfter, request);
  }
}

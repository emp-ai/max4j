package io.bittersweet.max.core.exception;

import feign.Request;
import feign.Request.HttpMethod;
import feign.RetryableException;
import java.util.Date;

public class MaxBotException extends RetryableException {

  public MaxBotException(
      int status,
      String message,
      HttpMethod httpMethod,
      Throwable cause,
      Date retryAfter,
      Request request
  ) {
    super(status, message, httpMethod, cause, retryAfter, request);
  }
}

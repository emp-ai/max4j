package io.bittersweet.max.core.exception;

import lombok.Getter;

@Getter
public class MaxBotException extends RuntimeException{

  private final int status;

  public MaxBotException(
      int status,
      String message
  ) {
    super(message);
    this.status = status;
  }

  public MaxBotException(String message, int status, Throwable cause) {
    super(message, cause);
    this.status = status;
  }
}

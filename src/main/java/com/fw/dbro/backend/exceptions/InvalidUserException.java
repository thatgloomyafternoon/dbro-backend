package com.fw.dbro.backend.exceptions;

public class InvalidUserException extends RuntimeException {

  public InvalidUserException(String message) {
    this(message, null);
  }

  public InvalidUserException(String message, Throwable cause) {
    super(message, cause);
  }

}

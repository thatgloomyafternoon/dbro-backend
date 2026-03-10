package com.fw.dbro.backend.configurations;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fw.dbro.backend.exceptions.InvalidUserException;
import com.fw.dbro.backend.web.responses.SimpleMessageResponse;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestErrorControllerAdvice {

  /**
   *
   */
  @ExceptionHandler({MethodArgumentNotValidException.class})
  @ResponseBody
  public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    String message = String.format("%s: %s",
      e.getClass().getSimpleName(),
      e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
    return ResponseEntity.badRequest().body(new SimpleMessageResponse(message));
  }

  /**
   *
   */
  @ExceptionHandler({JWTVerificationException.class})
  @ResponseBody
  public ResponseEntity<?> handleJwtVerificationException(JWTVerificationException e) {
    return ResponseEntity.badRequest().body(new SimpleMessageResponse(e.getClass().getSimpleName()));
  }

  /**
   *
   */
  @ExceptionHandler({
    MismatchedInputException.class,
    JsonParseException.class
  })
  @ResponseBody
  public ResponseEntity<?> handleJsonParseException(Exception e) {
    return ResponseEntity.badRequest().body(new SimpleMessageResponse(e.getClass().getSimpleName() + " : JSON parse error"));
  }

  /**
   *
   */
  @ExceptionHandler({InvalidUserException.class})
  @ResponseBody
  public ResponseEntity<?> handleInvalidUserException(InvalidUserException e) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new SimpleMessageResponse(e.getClass().getSimpleName()));
  }

  /**
   *
   */
  @ExceptionHandler({IllegalArgumentException.class})
  @ResponseBody
  public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
    return ResponseEntity.badRequest().body(new SimpleMessageResponse(e.getClass().getSimpleName() + " : " + e.getMessage()));
  }

  /**
   *
   */
  @ExceptionHandler({MissingServletRequestParameterException.class})
  @ResponseBody
  public ResponseEntity<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
    return ResponseEntity.badRequest().body(new SimpleMessageResponse(e.getClass().getSimpleName() + " : " + e.getMessage()));
  }

  /**
   *
   */
  @ExceptionHandler({IOException.class})
  @ResponseBody
  public ResponseEntity<?> handleIOException(IOException e) {
    return ResponseEntity.badRequest().body(new SimpleMessageResponse(e.getClass().getSimpleName() + " : " + e.getMessage()));
  }

}

package com.pdsc.ashpath.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler
{
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex)
  {
    List<String> errors = ex.getBindingResult()
      .getFieldErrors()
      .stream()
      .map(error -> error.getField() +": "+ error.getDefaultMessage())
      .collect(Collectors.toList());
    
    ErrorResponse response = new ErrorResponse(
      "Erro de validação",
      HttpStatus.BAD_REQUEST.value(),
      errors
    );

    return ResponseEntity.badRequest().body(response);
  }
}

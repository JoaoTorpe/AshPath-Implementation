package com.pdsc.ashpath.exception;

import java.util.List;

public class ErrorResponse
{
  private String message;
  private int status;
  private List<String> errors;
  
  public ErrorResponse()
  {}

  public ErrorResponse(String message, int status, List<String> errors)
  {
    this.message = message;
    this.status  = status;
    this.errors  = errors;
  }

  public void setMessage(String message)
  {
    this.message = message;
  }

  public String getMessage()
  {
    return message;
  }

  public void setStatus(int status)
  {
    this.status = status;
  }

  public int getStatus()
  {
    return status;
  }

  public void setErrors(List<String> errors)
  {
    this.errors = errors;
  }

  public List<String> getErrors()
  {
    return errors;
  }
}

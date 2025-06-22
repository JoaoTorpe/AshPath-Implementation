package com.pdsc.ashpath.domain.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CreateCremationEntryRequest
{
  @NotNull(message = "'cremationEntryId' field is required.")
  @Min(value = 1, message = "IDs values must be greater or equals than 1.")
  private Long necrotomistId;

  public CreateCremationEntryRequest()
  {}

  public CreateCremationEntryRequest(Long necrotomistId)
  {
    this.necrotomistId = necrotomistId;
  }

  public void setNecrotomistId(Long necrotomistId)
  {
    this.necrotomistId = necrotomistId;
  }

  public Long getNecrotomistId()
  {
    return this.necrotomistId;
  }
}

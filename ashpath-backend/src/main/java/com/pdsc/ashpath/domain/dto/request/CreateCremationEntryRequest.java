package com.pdsc.ashpath.domain.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CreateCremationEntryRequest
{
  @NotNull(message = "{NotNull.cremationEntry.necrotomistId}")
  @Min(value = 1, message = "{Min.cremationEntry.necrotomistId}")
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

package com.pdsc.ashpath.domain.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class AddDeceasedToCremationEntryRequest
{
  @NotNull(message = "'cremationEntryId' field is required.")
  @Min(value = 1, message = "IDs values must be greater or equals than 1.")
  private Long cremationEntryId;

  @NotNull(message = "'deceasedId' field is required.")
  @Min(value = 1, message = "IDs values must be greater or equals than 1.")
  private Long deceasedId;

  public AddDeceasedToCremationEntryRequest()
  {}
  
  public AddDeceasedToCremationEntryRequest(Long cremationEntryId, Long deceasedId)
  {
    this.cremationEntryId = cremationEntryId;
    this.deceasedId = deceasedId;
  }

  public void setCremationEntryId(Long cremationEntryId)
  {
    this.cremationEntryId = cremationEntryId;
  }

  public Long getCremationEntryId()
  {
    return this.cremationEntryId;
  }

  public void setDeceasedId(Long deceasedId)
  {
    this.deceasedId = deceasedId;
  }

  public Long getDeceasedId()
  {
    return this.deceasedId;
  }
}

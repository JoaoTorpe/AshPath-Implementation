package com.pdsc.ashpath.domain.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class AddDeceasedToCremationEntryRequest
{
  @NotNull(message = "{NotNull.cremationEntry.id}")
  @Min(value = 1, message = "{Min.cremationEntry.id}")
  private Long cremationEntryId;

  @NotNull(message = "{NotNull.deceased.id}")
  @Min(value = 1, message = "{Min.deceased.id}")
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

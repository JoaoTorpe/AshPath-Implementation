package com.pdsc.ashpath.domain.dto.request;

public class AddDeceasedToCremationEntryRequest
{
  private Long cremationEntryId;
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

package com.pdsc.ashpath.domain.dto.request;

public class CreateCremationEntryRequest
{
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

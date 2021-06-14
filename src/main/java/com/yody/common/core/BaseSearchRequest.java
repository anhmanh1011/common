package com.yody.common.core;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.yody.common.annotation.EnumMatch;
import com.yody.common.enums.SortEnum;
import java.io.Serializable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BaseSearchRequest implements Serializable {

  @Min(1)
  private int page = 1;
  @Max(200)
  private int limit = 30;
  @EnumMatch(type = SortEnum.class)
  private String sortType;
  private String sortColumn;

  public int getPage() {
    return page - 1;
  }
}

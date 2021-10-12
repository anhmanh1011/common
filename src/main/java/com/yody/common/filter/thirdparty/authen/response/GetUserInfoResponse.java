package com.yody.common.filter.thirdparty.authen.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GetUserInfoResponse {

  public String sub;
  public boolean emailVerified;
  public String name;
  public String preferredUsername;
  public String givenName;
  public String familyName;
  public String email;
  public String fullName;
  public String code;
}

package com.yody.common.filter.thirdparty.authen.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GetUserInfoResponse {

  private String sub;
  private boolean emailVerified;
  private String name;
  private String preferredUsername;
  private String givenName;
  private String familyName;
  private String email;
  private String fullName;
  private String code;
}

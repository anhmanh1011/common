package com.yody.common.filter.thirdparty.authen.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetUserInfoResponse {

  public String sub;
  public boolean emailVerified;
  public String name;
  public String preferredUsername;
  public String givenName;
  public String familyName;
  public String email;
}

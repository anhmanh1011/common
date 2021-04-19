package com.yody.common.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public abstract class Request implements Serializable {
    private static final long serialVersionUID = -8185669572280986533L;
    @JsonIgnore
    private String path;
    private String userId;
    private String userName;
    private String requestId;
    @JsonIgnore
    private String basicUserName;
    @JsonIgnore
    private String basicPassword;
}

package com.yody.common.http.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractHttpRequest implements Serializable {
    private static final long serialVersionUID = -8185669572280986533L;
    @JsonIgnore
    private String path;
}

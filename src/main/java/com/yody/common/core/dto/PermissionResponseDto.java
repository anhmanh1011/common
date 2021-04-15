package com.yody.common.core.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PermissionResponseDto {

    private List<Role> role;
    private Module modules;
    private DataWhitelist dataWhitelist;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Role {
        private String roleCode;
        private int position;
    }

    @Data
    @NoArgsConstructor
    public static class Module {
        private List<String> permissions;
    }

    @Data
    @NoArgsConstructor
    public static class DataWhitelist {
        private List<String> column;
    }
}

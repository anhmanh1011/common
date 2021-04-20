package com.yody.common.filter.thirdparty.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PermissionResponseDto {

    private List<Role> role;
    private Module modules;
    private DataWhitelist dataWhitelist;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Role {
        private String code;
        private String name;
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

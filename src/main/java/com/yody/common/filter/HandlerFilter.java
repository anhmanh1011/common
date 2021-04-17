package com.yody.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yody.common.anotaion.Permission;
import com.yody.common.core.dto.Result;
import com.yody.common.enums.CommonResponseCode;
import com.yody.common.enums.HeaderEnum;
import com.yody.common.thirdparty.request.PermissionRequestDto;
import com.yody.common.thirdparty.response.PermissionResponseDto;
import com.yody.common.thirdparty.services.AccountService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Objects;

import org.springframework.context.ApplicationContext;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HandlerFilter implements Filter {

    @Value("${yody.security.api.key}")
    private String apiKey;

    @Value("${yody.security.api.secret.key}")
    private String apiSecretKey;

    static final String REQUEST_MAPPING_HANDLER_MAPPING = "requestMappingHandlerMapping";

    private final ApplicationContext appContext;
    private final AccountService accountService;

    public HandlerFilter(ApplicationContext appContext, AccountService accountService) {
        this.appContext = appContext;
        this.accountService = accountService;
    }

    @Override
    public void init(FilterConfig filterConfig) {
        // do nothing
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        try {
            HandlerMethod handlerMethod;

            HttpServletResponse response = (HttpServletResponse) servletResponse;
            HttpServletRequest request = (HttpServletRequest) servletRequest;

            String userId = request.getHeader(HeaderEnum.HEADER_USER_ID.getValue());
            String userName = request.getHeader(HeaderEnum.HEADER_USER_NAME.getValue());
            String requestId = request.getHeader(HeaderEnum.HEADER_REQUEST_ID.getValue());
            String xApiKey = request.getHeader(HeaderEnum.X_API_KEY.getValue());
            String xApiSecretKey = request.getHeader(HeaderEnum.X_API_SECRET_KEY.getValue());

            ApiKeyVerifyRequestWrapper requestWrapper = new ApiKeyVerifyRequestWrapper(request);
            JSONObject dataRequest;
            if (requestWrapper.getBody() != null) {
                dataRequest = new JSONObject(requestWrapper.getBody());
            } else {
                dataRequest = new JSONObject();
            }
            dataRequest.put(HeaderEnum.HEADER_USER_ID.getValue(), userId);
            dataRequest.put(HeaderEnum.HEADER_USER_NAME.getValue(), userName);
            dataRequest.put(HeaderEnum.HEADER_REQUEST_ID.getValue(), requestId);
            requestWrapper.setBody(dataRequest.toString());
            if (null != apiKey && null != apiSecretKey) {
                if (apiKey.equals(xApiKey) && apiSecretKey.equals(xApiSecretKey)) {
                    filterChain.doFilter(requestWrapper, servletResponse);
                } else {
                    buildErrorUnAuthorize(response, requestId, 401,
                        CommonResponseCode.UNAUTHORIZE.getValue(), CommonResponseCode.UNAUTHORIZE.getDisplayName());
                }
            } else {
                RequestMappingHandlerMapping req2HandlerMapping = (RequestMappingHandlerMapping) appContext.getBean(REQUEST_MAPPING_HANDLER_MAPPING);
                HandlerExecutionChain handlerExeChain = req2HandlerMapping.getHandler(request);
                if (Objects.nonNull(handlerExeChain)) {
                    handlerMethod = (HandlerMethod) handlerExeChain.getHandler();

                    Method method = handlerMethod.getMethod();
                    Permission annotation = AnnotationUtils.findAnnotation(method, Permission.class);
                    String[] permissionTypes = annotation != null ? annotation.permissionType() : null;
                    if (permissionTypes == null || permissionTypes.length <= 0) {
                        filterChain.doFilter(requestWrapper, servletResponse);
                        return;
                    }
                    PermissionRequestDto requestDto = new PermissionRequestDto();
                    requestDto.setRequestId(requestId);
                    requestDto.setUserId(userId);
                    requestDto.setUserName(userName);
                    Result<PermissionResponseDto> result = accountService.getPermissionInfo(requestDto);
                    if (result == null) {
                        buildErrorUnAuthorize(response, requestId, 401,
                            CommonResponseCode.UNAUTHORIZE.getValue(), CommonResponseCode.UNAUTHORIZE.getDisplayName());
                        return;
                    }
                    PermissionResponseDto permissionsDto = result.getData();

                    if (null == permissionsDto.getModules() || CollectionUtils.isEmpty(permissionsDto.getModules().getPermissions())) {
                        buildErrorUnAuthorize(response, requestId, 401,
                            CommonResponseCode.UNAUTHORIZE.getValue(), CommonResponseCode.UNAUTHORIZE.getDisplayName());
                        return;
                    } else {
                        int countPer = 0;
                        for (String per : permissionTypes) {
                            for (String permissionCode : permissionsDto.getModules().getPermissions()) {
                                if (per.contains(permissionCode)) {
                                    countPer++;
                                }
                            }
                        }
                        if (countPer == 0) {
                            buildErrorUnAuthorize(response, requestId, 401,
                                CommonResponseCode.UNAUTHORIZE.getValue(), CommonResponseCode.UNAUTHORIZE.getDisplayName());
                            return;
                        }
                    }
                }
                filterChain.doFilter(requestWrapper, servletResponse);
            }
        } catch (Exception exception) {
            buildErrorUnAuthorize((HttpServletResponse) servletRequest, "", 500,
                CommonResponseCode.INTERNAL_ERROR.getValue(), CommonResponseCode.INTERNAL_ERROR.getDisplayName());
        }
    }

    @Override
    public void destroy() {
        // do nothing
    }

    private byte[] restResponseBytes(Object result) throws IOException {
        String serialized = new ObjectMapper().writeValueAsString(result);
        return serialized.getBytes();
    }

    private void buildErrorUnAuthorize(HttpServletResponse response, String requestId,
                                       int httpStatus, int errorCode, String message) {
        response.setStatus(httpStatus);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try {
            response.getOutputStream().write(restResponseBytes(
                new Result(errorCode, message, null, requestId)));
        } catch (IOException ignored) {
        }
    }
}

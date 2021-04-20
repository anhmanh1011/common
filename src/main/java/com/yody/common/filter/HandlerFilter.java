package com.yody.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yody.common.anotaion.Permission;
import com.yody.common.core.dto.Result;
import com.yody.common.enums.CommonResponseCode;
import com.yody.common.enums.HeaderEnum;
import com.yody.common.filter.thirdparty.request.PermissionRequestDto;
import com.yody.common.filter.thirdparty.response.PermissionResponseDto;
import com.yody.common.filter.thirdparty.services.AuthService;
import com.yody.common.utility.BasicAuthorization;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.UUID;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HandlerFilter implements Filter {

    @Value("${yody.security.basic.username}")
    private String basicUserName;

    @Value("${yody.security.basic.password}")
    private String basicPassword;

    private static final String REQUEST_ID_LOG_VAR_NAME = "request_id";

    static final String REQUEST_MAPPING_HANDLER_MAPPING = "requestMappingHandlerMapping";

    private final ApplicationContext appContext;
    private final AuthService authService;

    public HandlerFilter(ApplicationContext appContext, AuthService authService) {
        this.appContext = appContext;
        this.authService = authService;
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
            String authorization = request.getHeader(HeaderEnum.HEADER_AUTHORIZATION.getValue());

            if (requestId == null) {
                requestId = UUID.randomUUID().toString();
            }
            MDC.put(REQUEST_ID_LOG_VAR_NAME, requestId);

            VerifyRequestWrapper requestWrapper = new VerifyRequestWrapper(request);
            JSONObject dataRequest;
            if (requestWrapper.getBody() != null && !"".equals(requestWrapper.getBody())) {
                dataRequest = new JSONObject(requestWrapper.getBody());
            } else {
                dataRequest = new JSONObject();
            }
            dataRequest.put(HeaderEnum.HEADER_USER_ID.getValue(), userId);
            dataRequest.put(HeaderEnum.HEADER_USER_NAME.getValue(), userName);
            dataRequest.put(HeaderEnum.HEADER_REQUEST_ID.getValue(), requestId);
            requestWrapper.setBody(dataRequest.toString());

            if (null != authorization) {
                boolean isAuthorization = BasicAuthorization.checkBasicAuthorization(authorization, basicUserName, basicPassword);
                if (isAuthorization) {
                    filterChain.doFilter(requestWrapper, servletResponse);
                } else {
                    buildErrorResponse(response, requestId, HttpServletResponse.SC_UNAUTHORIZED,
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
                    Result<PermissionResponseDto> result = authService.getPermissionInfo(requestDto);
                    if (result == null) {
                        buildErrorResponse(response, requestId, HttpServletResponse.SC_UNAUTHORIZED,
                            CommonResponseCode.UNAUTHORIZE.getValue(), CommonResponseCode.UNAUTHORIZE.getDisplayName());
                        return;
                    }
                    PermissionResponseDto permissionsDto = result.getData();

                    if (null == permissionsDto.getModules() || CollectionUtils.isEmpty(permissionsDto.getModules().getPermissions())) {
                        buildErrorResponse(response, requestId, HttpServletResponse.SC_UNAUTHORIZED,
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
                            buildErrorResponse(response, requestId, HttpServletResponse.SC_UNAUTHORIZED,
                                CommonResponseCode.UNAUTHORIZE.getValue(), CommonResponseCode.UNAUTHORIZE.getDisplayName());
                            return;
                        }
                    }
                }
                filterChain.doFilter(requestWrapper, servletResponse);
            }
        } catch (Exception exception) {
            buildErrorResponse((HttpServletResponse) servletRequest, UUID.randomUUID().toString(),
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR, CommonResponseCode.INTERNAL_ERROR.getValue(),
                CommonResponseCode.INTERNAL_ERROR.getDisplayName());
        }
    }

    @Override
    public void destroy() {
        MDC.remove(REQUEST_ID_LOG_VAR_NAME);
    }

    private byte[] restResponseBytes(Object result) throws IOException {
        String serialized = new ObjectMapper().writeValueAsString(result);
        return serialized.getBytes();
    }

    private void buildErrorResponse(HttpServletResponse response, String requestId,
                                    int httpStatus, int errorCode, String message) {
        response.setStatus(httpStatus);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try {
            response.getOutputStream().write(restResponseBytes(Result.error(requestId, errorCode, message)));
        } catch (IOException ignored) {
        }
    }
}

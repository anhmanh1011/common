package com.yody.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yody.common.annotation.Permission;
import com.yody.common.constant.HeaderInfo;
import com.yody.common.core.RequestInfo;
import com.yody.common.core.dto.PermissionResponseDto;
import com.yody.common.core.dto.Result;
import com.yody.common.enums.CommonResponseCode;
import com.yody.common.enums.HeaderEnum;
import com.yody.common.filter.constant.FieldConstant;
import com.yody.common.filter.constant.PermissionConstant;
import com.yody.common.filter.thirdparty.authen.request.GetUserInfoRequest;
import com.yody.common.filter.thirdparty.authen.response.GetUserInfoResponse;
import com.yody.common.filter.thirdparty.authen.services.AuthenService;
import com.yody.common.filter.thirdparty.authoz.request.PermissionRequestDto;
import com.yody.common.filter.thirdparty.authoz.services.AuthService;
import com.yody.common.utility.BasicAuthorization;
import com.yody.common.utility.Strings;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Component
@Order(-2147483647)
@Slf4j
public class HandlerFilter implements Filter {

    @Value("${yody.security.basic.username}")
    private String basicUserName;

    @Value("${yody.security.basic.password}")
    private String basicPassword;

    private final ApplicationContext appContext;
    private final AuthService authService;
    private final AuthenService authenService;

    public HandlerFilter(ApplicationContext appContext, AuthService authService, AuthenService authenService) {
        this.appContext = appContext;
        this.authService = authService;
        this.authenService = authenService;
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        try {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            if (request.getRequestURI().contains("api-docs") || request.getRequestURI().contains("swagger-ui") ||
                request.getRequestURI().contains("favicon.ico") || request.getRequestURI().contains("swagger-config") ||
                request.getRequestURI().contains("/actuator/health") || request.getRequestURI().contains("/actuator/info") ||
                request.getRequestURI().contains("/accounts/login") || request.getMethod().equalsIgnoreCase("OPTIONS") ||
                request.getRequestURI().contains("/public/") || (request.getRequestURI().contains("/profile") && !request.getRequestURI().contains("/user/profile")) ||
                request.getRequestURI().contains("/ping") || request.getRequestURI().contains("/login")) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            RequestInfo requestInfo = new RequestInfo();
            if (!this.getUserInfo(request, requestInfo)) {
                buildErrorResponse(response, requestInfo.getRequestId(), HttpServletResponse.SC_OK,
                    CommonResponseCode.UNAUTHORIZE.getValue(), CommonResponseCode.UNAUTHORIZE.getDisplayName());
                return;
            }
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart && this.processMultipartRequest(request, response, requestInfo, filterChain)) {
                return;
            } else if (!isMultipart && this.processRequest(request, response, requestInfo, filterChain)) {
                return;
            }
            buildErrorResponse(response, requestInfo.getRequestId(), HttpServletResponse.SC_OK,
                CommonResponseCode.UNAUTHORIZE.getValue(), CommonResponseCode.UNAUTHORIZE.getDisplayName());
        } catch (Exception exception) {
            log.error("Error when process check permission: {}", exception.getMessage());
            buildErrorResponse((HttpServletResponse) servletResponse, UUID.randomUUID().toString(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                CommonResponseCode.INTERNAL_ERROR.getValue(), CommonResponseCode.INTERNAL_ERROR.getDisplayName());
        }
    }

    public boolean processMultipartRequest(HttpServletRequest request, HttpServletResponse servletResponse, RequestInfo requestInfo, FilterChain filterChain) {
        try {
            if (requestInfo.isBasicAuth()) {
                if (!checkBasicAuth(requestInfo)) return false;
            } else if (StringUtils.isBlank(requestInfo.getOperatorKcId()) || !checkPermissionByUserId(requestInfo, request)) {
                buildErrorResponse(servletResponse, requestInfo.getRequestId(), HttpServletResponse.SC_OK,
                    CommonResponseCode.FORBIDDEN.getValue(), CommonResponseCode.FORBIDDEN.getDisplayName());
                return true;
            }
            MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);

            if (request.getMethod().equals(HttpMethod.POST.name())) {
                multipartRequest.setAttribute(FieldConstant.CREATED_BY, requestInfo.getCode());
                multipartRequest.setAttribute(FieldConstant.CREATED_NAME, requestInfo.getFullName());
            } else if (request.getMethod().equals(HttpMethod.PUT.name())) {
                multipartRequest.setAttribute(FieldConstant.UPDATED_BY, requestInfo.getCode());
                multipartRequest.setAttribute(FieldConstant.UPDATED_NAME, requestInfo.getFullName());
            }
            multipartRequest.setAttribute(FieldConstant.OPERATOR_KC_ID, requestInfo.getOperatorKcId());
            multipartRequest.setAttribute(FieldConstant.OPERATOR_LOGIN_ID, requestInfo.getOperatorLoginId());
            multipartRequest.setAttribute(FieldConstant.REQUEST_ID, requestInfo.getRequestId());
            if(!Strings.isEmpty(requestInfo.getCode())) {
                multipartRequest.getRequestHeaders().put(HeaderInfo.X_USER_CODE, Collections.singletonList(requestInfo.getCode()));
            }
            if(!Strings.isEmpty(requestInfo.getFullName())) {
                multipartRequest.getRequestHeaders().put(HeaderInfo.X_USER_NAME, Collections.singletonList(requestInfo.getFullName()));
            }
            filterChain.doFilter(multipartRequest, servletResponse);
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
    }

    public boolean processRequest(HttpServletRequest request, HttpServletResponse servletResponse, RequestInfo requestInfo, FilterChain filterChain) {
        try {
            if (requestInfo.isBasicAuth()) {
                if (!checkBasicAuth(requestInfo)) return false;
            } else if (StringUtils.isBlank(requestInfo.getOperatorKcId()) || !checkPermissionByUserId(requestInfo, request)) {
                buildErrorResponse(servletResponse, requestInfo.getRequestId(), HttpServletResponse.SC_OK,
                    CommonResponseCode.FORBIDDEN.getValue(), CommonResponseCode.FORBIDDEN.getDisplayName());
                return true;
            }
            VerifyRequestWrapper requestWrapper = new VerifyRequestWrapper(request);
            JSONObject dataRequest;
            if (requestWrapper.getBody() != null && !"".equals(requestWrapper.getBody())) {
                dataRequest = new JSONObject(requestWrapper.getBody());
            } else {
                dataRequest = new JSONObject();
            }

            if (request.getMethod().equals(HttpMethod.POST.name())) {
                dataRequest.put(FieldConstant.CREATED_BY, requestInfo.getCode());
                dataRequest.put(FieldConstant.CREATED_NAME, requestInfo.getFullName());
            } else if (request.getMethod().equals(HttpMethod.PUT.name()) || request.getMethod().equals(HttpMethod.DELETE.name())) {
                dataRequest.put(FieldConstant.UPDATED_BY, requestInfo.getCode());
                dataRequest.put(FieldConstant.UPDATED_NAME, requestInfo.getFullName());
            }
            dataRequest.put(FieldConstant.OPERATOR_KC_ID, requestInfo.getOperatorKcId());
            dataRequest.put(FieldConstant.OPERATOR_NAME, requestInfo.getOperatorName());
            dataRequest.put(FieldConstant.REQUEST_ID, requestInfo.getRequestId());
            //todo: can check lai li do tai sao null
            if (Strings.isEmpty(requestInfo.getOperatorName())) {
                dataRequest.put(FieldConstant.CREATED_NAME, "admin");
                dataRequest.put(FieldConstant.UPDATED_NAME, "admin");
            }
            if (Strings.isEmpty(requestInfo.getOperatorLoginId())) {
                dataRequest.put(FieldConstant.CREATED_BY, "admin");
                dataRequest.put(FieldConstant.UPDATED_BY, "admin");
            }
            requestWrapper.setBody(dataRequest.toString());
            if(!Strings.isEmpty(requestInfo.getCode())) {
                requestWrapper.addHeader(HeaderInfo.X_USER_CODE, requestInfo.getCode());
            }
            if(!Strings.isEmpty(requestInfo.getFullName())) {
                requestWrapper.addHeader(HeaderInfo.X_USER_NAME, requestInfo.getFullName());
            }
            filterChain.doFilter(requestWrapper, servletResponse);
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
    }

    @Override
    public void destroy() {
        MDC.remove(FieldConstant.REQUEST_ID_LOG_VAR_NAME);
    }


    private byte[] restResponseBytes(Object result) throws IOException {
        String serialized = new ObjectMapper().writeValueAsString(result);
        return serialized.getBytes();
    }

    private void buildErrorResponse(HttpServletResponse response, String requestId, int httpStatus, int errorCode, String message) {
        response.setStatus(httpStatus);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try {
            response.getOutputStream().write(restResponseBytes(Result.error(requestId, errorCode, message)));
        } catch (IOException ignored) {
            ignored.printStackTrace();
            log.error(ignored.toString());
        }
    }

    private boolean getUserInfo(HttpServletRequest request, RequestInfo requestInfo) {
        String authorization = request.getHeader(HeaderEnum.HEADER_AUTHORIZATION.getValue());
        if (StringUtils.isBlank(authorization)) {
            return false;
        }
        requestInfo.setAuthorization(authorization);

        String token = authorization.split(" ")[1];
        if (authorization.toLowerCase().contains("basic")) {
            requestInfo.setBasicAuth(true);
            requestInfo.setOperatorKcId(request.getHeader(FieldConstant.OPERATOR_KC_ID));
            requestInfo.setOperatorLoginId(FieldConstant.OPERATOR_LOGIN_ID);
            requestInfo.setRequestId(FieldConstant.REQUEST_ID);
            return true;
        }
        GetUserInfoRequest getUserInfoRequest = new GetUserInfoRequest();
        getUserInfoRequest.setToken(String.format("Bearer %s", token));

        GetUserInfoResponse getUserInfoResponse = authenService.getUserInfo(getUserInfoRequest);
        if (getUserInfoResponse == null || StringUtils.isBlank(getUserInfoResponse.getName()) || StringUtils.isBlank(getUserInfoResponse.getCode()) || StringUtils.isBlank(
            getUserInfoResponse.getFullName())) {
            return false;
        }
        requestInfo.setOperatorKcId(getUserInfoResponse.getSub());
        requestInfo.setOperatorLoginId(getUserInfoResponse.getName());
        requestInfo.setOperatorName(getUserInfoResponse.getPreferredUsername());
        requestInfo.setFullName(getUserInfoResponse.getFullName());
        requestInfo.setCode(getUserInfoResponse.getCode());
        String requestId = request.getHeader(HeaderEnum.HEADER_REQUEST_ID.getValue());
        if (StringUtils.isBlank(requestId)) {
            requestId = UUID.randomUUID().toString();
        }
        requestInfo.setRequestId(requestId);
        MDC.put(FieldConstant.REQUEST_ID_LOG_VAR_NAME, requestId);
        return true;
    }

    private boolean checkBasicAuth(RequestInfo requestInfo) {
        return BasicAuthorization.checkBasicAuthorization(requestInfo.getAuthorization(), basicUserName, basicPassword);
    }

    @SneakyThrows
    private boolean checkPermissionByUserId(RequestInfo requestInfo, HttpServletRequest request) {
        RequestMappingHandlerMapping req2HandlerMapping = appContext.getBean(RequestMappingHandlerMapping.class);
        HandlerExecutionChain handlerExeChain = req2HandlerMapping.getHandler(request);
        if (!Objects.nonNull(handlerExeChain)) {
            return false;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handlerExeChain.getHandler();

        Method method = handlerMethod.getMethod();
        Permission annotation = AnnotationUtils.findAnnotation(method, Permission.class);
        String[] permissionTypes = annotation != null ? annotation.permissionType() : null;
        if (permissionTypes == null || permissionTypes.length <= 0) {
            return true;
        }
        PermissionRequestDto requestDto = new PermissionRequestDto();
        requestDto.setRequestId(requestInfo.getRequestId());
        requestDto.setUserId(requestInfo.getOperatorKcId());
        requestDto.setUserName(requestInfo.getOperatorName());
        Result<PermissionResponseDto> result = authService.getPermissionInfo(requestDto);
        final String[] grantedPermissions = Optional.ofNullable(result)
            .map(Result::getData)
            .map(PermissionResponseDto::getPermissions)
            .orElseGet(Collections::emptyList)
            .stream()
            .distinct()
            .toArray(String[]::new);
        return grantedPermissions.length > 0
            && Stream.concat(
                Stream.of(PermissionConstant.ADMIN_ALL),
                Stream.of(permissionTypes)
            )
            .anyMatch(per -> StringUtils.equalsAnyIgnoreCase(per, grantedPermissions));
    }
}

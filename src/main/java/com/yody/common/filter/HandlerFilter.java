package com.yody.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yody.common.annotation.Permission;
import com.yody.common.core.dto.Result;
import com.yody.common.enums.CommonResponseCode;
import com.yody.common.enums.HeaderEnum;
import com.yody.common.filter.constant.FieldConstant;
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
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class HandlerFilter implements Filter {

  @Value("${yody.security.basic.username}")
  private String basicUserName;

  @Value("${yody.security.basic.password}")
  private String basicPassword;

  private static final String REQUEST_ID_LOG_VAR_NAME = "request_id";

  private final ApplicationContext appContext;
  private final AuthService authService;

  String userId = "";
  String userName = "";
  String requestId = "";
  String authorization = "";
  String fullName = "";

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

      HttpServletResponse response = (HttpServletResponse) servletResponse;
      HttpServletRequest request = (HttpServletRequest) servletRequest;
      if(request.getRequestURI().contains("/v2/api-docs")
          || request.getRequestURI().contains("/actuator/health")
          || request.getRequestURI().contains("/actuator/info")){
        filterChain.doFilter(servletRequest,servletResponse);
        return;
      }
      boolean isMultipart = ServletFileUpload.isMultipartContent(request);
      if (isMultipart) {
        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
        userId = multipartRequest.getHeader(HeaderEnum.HEADER_USER_ID.getValue());
        userName = multipartRequest.getHeader(HeaderEnum.HEADER_USER_NAME.getValue());
        fullName = multipartRequest.getHeader(HeaderEnum.HEADER_FULL_NAME.getValue());
        authorization = multipartRequest.getHeader(HeaderEnum.HEADER_AUTHORIZATION.getValue());
        requestId = multipartRequest.getHeader(HeaderEnum.HEADER_REQUEST_ID.getValue());
        if (requestId == null || requestId.isEmpty()) {
          requestId = UUID.randomUUID().toString();
        }
        MDC.put(REQUEST_ID_LOG_VAR_NAME, requestId);

        multipartRequest.setAttribute(FieldConstant.USER_ID, userId);
        multipartRequest.setAttribute(FieldConstant.USER_NAME, userName);
        multipartRequest.setAttribute(FieldConstant.REQUEST_ID, requestId);
        multipartRequest.setAttribute(FieldConstant.CREATED_BY, userName);
        multipartRequest.setAttribute(FieldConstant.CREATED_NAME, fullName);
        multipartRequest.setAttribute(FieldConstant.UPDATED_BY, userName);
        multipartRequest.setAttribute(FieldConstant.UPDATED_NAME, fullName);

        if (null != authorization && !"".equals(authorization) && checkBasicAuth()) {
          filterChain.doFilter(multipartRequest, servletResponse);
          return;
        } else if (!"".equals(userId) && null != userId && checkPermissionByUserId(userId, request)) {
          filterChain.doFilter(multipartRequest, servletResponse);
          return;
        }
      } else {
        userId = request.getHeader(HeaderEnum.HEADER_USER_ID.getValue());
        userName = request.getHeader(HeaderEnum.HEADER_USER_NAME.getValue());
        fullName = request.getHeader(HeaderEnum.HEADER_FULL_NAME.getValue());
        requestId = request.getHeader(HeaderEnum.HEADER_REQUEST_ID.getValue());
        authorization = request.getHeader(HeaderEnum.HEADER_AUTHORIZATION.getValue());

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
        dataRequest.put(FieldConstant.USER_ID, userId);
        dataRequest.put(FieldConstant.USER_NAME, userName);
        dataRequest.put(FieldConstant.REQUEST_ID, requestId);
        dataRequest.put(FieldConstant.CREATED_BY, userName);
        dataRequest.put(FieldConstant.CREATED_NAME, fullName);
        dataRequest.put(FieldConstant.UPDATED_BY, userName);
        dataRequest.put(FieldConstant.UPDATED_NAME, fullName);
        requestWrapper.setBody(dataRequest.toString());
        if (null != authorization && !"".equals(authorization) && checkBasicAuth()) {
          filterChain.doFilter(requestWrapper, servletResponse);
          return;
        } else if (!"".equals(userId) && null != userId && checkPermissionByUserId(userId, request)) {
          filterChain.doFilter(requestWrapper, servletResponse);
          return;
        }
      }
      buildErrorResponse(response, requestId, HttpServletResponse.SC_UNAUTHORIZED, CommonResponseCode.UNAUTHORIZE.getValue(), CommonResponseCode.UNAUTHORIZE.getDisplayName());
    } catch (Exception exception) {
      log.error("Error when process check permission: {}", exception.getMessage());
      buildErrorResponse((HttpServletResponse) servletResponse, UUID.randomUUID().toString(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
          CommonResponseCode.INTERNAL_ERROR.getValue(), CommonResponseCode.INTERNAL_ERROR.getDisplayName());
    }
  }

  @Override
  public void destroy() {
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
      log.error(ignored.toString());
    }
  }

  private boolean checkBasicAuth() {
    return BasicAuthorization.checkBasicAuthorization(authorization, basicUserName, basicPassword);
  }

  @SneakyThrows
  private boolean checkPermissionByUserId(String userId, HttpServletRequest request) {
    RequestMappingHandlerMapping req2HandlerMapping = (RequestMappingHandlerMapping) appContext.getBean(RequestMappingHandlerMapping.class);
    HandlerExecutionChain handlerExeChain = req2HandlerMapping.getHandler(request);
    if (!Objects.nonNull(handlerExeChain)) {
      return true;
    }

    HandlerMethod handlerMethod = (HandlerMethod) handlerExeChain.getHandler();

    Method method = handlerMethod.getMethod();
    Permission annotation = AnnotationUtils.findAnnotation(method, Permission.class);
    String[] permissionTypes = annotation != null ? annotation.permissionType() : null;
    if (permissionTypes == null || permissionTypes.length <= 0) {
      return true;
    }
    PermissionRequestDto requestDto = new PermissionRequestDto();
    requestDto.setRequestId(requestId);
    requestDto.setUserId(userId);
    requestDto.setUserName(userName);
    Result<PermissionResponseDto> result = authService.getPermissionInfo(requestDto);
    if (result == null) {
      return false;
    }
    PermissionResponseDto permissionsDto = result.getData();

    if (null == permissionsDto.getModules() || CollectionUtils.isEmpty(permissionsDto.getModules().getPermissions())) {
      return false;
    }

    int countPer = 0;
    for (String per : permissionTypes) {
      for (String permissionCode : permissionsDto.getModules().getPermissions()) {
        if (per.contains(permissionCode)) {
          countPer++;
          break;
        }
      }
    }
    return countPer != 0;
  }
}

package com.yody.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yody.common.annotation.Permission;
import com.yody.common.constant.HeaderInfo;
import com.yody.common.core.RequestInfo;
import com.yody.common.core.dto.Result;
import com.yody.common.enums.CommonResponseCode;
import com.yody.common.enums.HeaderEnum;
import com.yody.common.filter.constant.FieldConstant;
import com.yody.common.filter.constant.PermissionConstant;
import com.yody.common.filter.thirdparty.authen.request.GetUserInfoRequest;
import com.yody.common.filter.thirdparty.authen.response.GetUserInfoResponse;
import com.yody.common.filter.thirdparty.authen.services.AuthenService;
import com.yody.common.filter.thirdparty.authoz.request.PermissionRequestDto;
import com.yody.common.filter.thirdparty.authoz.response.PermissionResponseDto;
import com.yody.common.filter.thirdparty.authoz.services.AuthService;
import com.yody.common.utility.BasicAuthorization;
import com.yody.common.utility.Strings;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collections;
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
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

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

  RequestInfo requestInfo;

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
          request.getRequestURI().contains("/public/") || request.getRequestURI().contains("/profile") ||
          request.getRequestURI().contains("/ping")) {
        filterChain.doFilter(servletRequest, servletResponse);
        return;
      }
      if (!this.getUserInfo(request)) {
        buildErrorResponse(response, requestInfo.getRequestId(), HttpServletResponse.SC_UNAUTHORIZED,
            CommonResponseCode.UNAUTHORIZE.getValue(), CommonResponseCode.UNAUTHORIZE.getDisplayName());
        return;
      }
      boolean isMultipart = ServletFileUpload.isMultipartContent(request);
      if (isMultipart && this.processMultipartRequest(request, response, filterChain)) {
        return;
      } else if (!isMultipart && this.processRequest(request, response, filterChain)) {
        return;
      }
      buildErrorResponse(response, requestInfo.getRequestId(), HttpServletResponse.SC_UNAUTHORIZED,
          CommonResponseCode.UNAUTHORIZE.getValue(), CommonResponseCode.UNAUTHORIZE.getDisplayName());
    } catch (Exception exception) {
      log.error("Error when process check permission: {}", exception.getMessage());
      buildErrorResponse((HttpServletResponse) servletResponse, UUID.randomUUID().toString(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
          CommonResponseCode.INTERNAL_ERROR.getValue(), CommonResponseCode.INTERNAL_ERROR.getDisplayName());
    }
  }

  public boolean processMultipartRequest(HttpServletRequest request, HttpServletResponse servletResponse, FilterChain filterChain) {
    try {
      if (requestInfo.isBasicAuth()) {
        if (!checkBasicAuth()) return false;
      } else if (StringUtils.isBlank(requestInfo.getOperatorKcId()) || !checkPermissionByUserId(requestInfo.getOperatorKcId(), request)) {
        buildErrorResponse(servletResponse, requestInfo.getRequestId(), HttpServletResponse.SC_FORBIDDEN, CommonResponseCode.FORBIDDEN.getValue(),
            CommonResponseCode.FORBIDDEN.getDisplayName());
        return true;
      }
      MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
      MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);

      if (request.getMethod().equals(HttpMethod.POST.name())) {
        multipartRequest.setAttribute(FieldConstant.CREATED_BY, requestInfo.getOperatorLoginId());
        multipartRequest.setAttribute(FieldConstant.CREATED_NAME, requestInfo.getOperatorName());
      } else if (request.getMethod().equals(HttpMethod.PUT.name())) {
        multipartRequest.setAttribute(FieldConstant.UPDATED_BY, requestInfo.getOperatorLoginId());
        multipartRequest.setAttribute(FieldConstant.UPDATED_NAME, requestInfo.getOperatorName());
      }
      multipartRequest.setAttribute(FieldConstant.OPERATOR_KC_ID, requestInfo.getOperatorKcId());
      multipartRequest.setAttribute(FieldConstant.OPERATOR_LOGIN_ID, requestInfo.getOperatorLoginId());
      multipartRequest.setAttribute(FieldConstant.REQUEST_ID, requestInfo.getRequestId());

      multipartRequest.getRequestHeaders().put(HeaderInfo.X_USER_NAME, Collections.singletonList(requestInfo.getFullName()));
      multipartRequest.getRequestHeaders().put(HeaderInfo.X_USER_CODE, Collections.singletonList(requestInfo.getCode()));
      filterChain.doFilter(multipartRequest, servletResponse);
      return true;
    } catch (Exception ex) {
      log.error(ex.getMessage());
      return false;
    }
  }

  public boolean processRequest(HttpServletRequest request, HttpServletResponse servletResponse, FilterChain filterChain) {
    try {
      if (requestInfo.isBasicAuth()) {
        if (!checkBasicAuth()) return false;
      } else if (StringUtils.isBlank(requestInfo.getOperatorKcId()) || !checkPermissionByUserId(requestInfo.getOperatorKcId(), request)) {
        buildErrorResponse(servletResponse, requestInfo.getRequestId(), HttpServletResponse.SC_FORBIDDEN,
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
        dataRequest.put(FieldConstant.CREATED_BY, requestInfo.getOperatorLoginId());
        dataRequest.put(FieldConstant.CREATED_NAME, requestInfo.getOperatorName());
      } else if (request.getMethod().equals(HttpMethod.PUT.name()) || request.getMethod().equals(HttpMethod.DELETE.name())) {
        dataRequest.put(FieldConstant.UPDATED_BY, requestInfo.getOperatorLoginId());
        dataRequest.put(FieldConstant.UPDATED_NAME, requestInfo.getOperatorName());
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
      requestWrapper.addHeader(HeaderInfo.X_USER_NAME, requestInfo.getFullName());
      requestWrapper.addHeader(HeaderInfo.X_USER_CODE, requestInfo.getCode());
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
      log.error(ignored.toString());
    }
  }

  private boolean getUserInfo(HttpServletRequest request) {
    requestInfo = new RequestInfo();
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
    requestInfo.setOperatorKcId(getUserInfoResponse.getSub());
    requestInfo.setOperatorLoginId(getUserInfoResponse.getName());
    requestInfo.setOperatorName(getUserInfoResponse.getPreferredUsername());
    requestInfo.setFullName(getUserInfoResponse.getFullName());
    requestInfo.setCode(getUserInfoResponse.getCode());
    String requestId = request.getHeader(HeaderEnum.HEADER_REQUEST_ID.getValue());
    if (requestId == null || requestId.isEmpty()) {
      requestId = UUID.randomUUID().toString();
    }
    requestInfo.setRequestId(requestId);
    MDC.put(FieldConstant.REQUEST_ID_LOG_VAR_NAME, requestId);
    return true;
  }

  private boolean checkBasicAuth() {
    return BasicAuthorization.checkBasicAuthorization(requestInfo.getAuthorization(), basicUserName, basicPassword);
  }

  @SneakyThrows
  private boolean checkPermissionByUserId(String userId, HttpServletRequest request) {
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
    requestDto.setUserId(userId);
    requestDto.setUserName(requestInfo.getOperatorName());
    Result<PermissionResponseDto> result = authService.getPermissionInfo(requestDto);
    if (result == null) {
      return false;
    }
    PermissionResponseDto permissionsDto = result.getData();

    if (null == permissionsDto.getModules() || CollectionUtils.isEmpty(permissionsDto.getModules().getPermissions())) {
      return false;
    }
    if (permissionsDto.getModules().getPermissions().contains(PermissionConstant.ADMIN_ALL)) {
      return true;
    }
    for (String per : permissionTypes) {
      for (String permissionCode : permissionsDto.getModules().getPermissions()) {
        if (per.contains(permissionCode)) {
          return true;
        }
      }
    }
    return false;
  }
}

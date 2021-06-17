package com.yody.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yody.common.annotation.Permission;
import com.yody.common.core.RequestInfo;
import com.yody.common.core.dto.Result;
import com.yody.common.enums.CommonResponseCode;
import com.yody.common.enums.HeaderEnum;
import com.yody.common.filter.constant.FieldConstant;
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
import org.springframework.core.Ordered;
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
@Order(-2147483648)
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
    // do nothing
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
    try {
      HttpServletResponse response = (HttpServletResponse) servletResponse;
      HttpServletRequest request = (HttpServletRequest) servletRequest;
      if (request.getRequestURI().contains("/v2/api-docs") || request.getRequestURI().contains("/actuator/health") || request.getRequestURI().contains("/actuator/info") || request
          .getRequestURI().contains("/accounts/login")) {
        filterChain.doFilter(servletRequest, servletResponse);
        return;
      }
      if (this.getUserInfo(request)) {
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

      filterChain.doFilter(multipartRequest, servletResponse);
      return (requestInfo.isBasicAuth() && checkBasicAuth()) || (StringUtils.isNotBlank(requestInfo.getOperatorKcId())
          && checkPermissionByUserId(requestInfo.getOperatorKcId(), request));
    } catch (Exception ex) {
      log.error(ex.getMessage());
      return false;
    }
  }

  public boolean processRequest(HttpServletRequest request, HttpServletResponse servletResponse, FilterChain filterChain) {
    try {
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
      requestWrapper.setBody(dataRequest.toString());
      filterChain.doFilter(request, servletResponse);

      return (requestInfo.isBasicAuth() && checkBasicAuth()) || (StringUtils.isNotBlank(requestInfo.getOperatorKcId())
          && checkPermissionByUserId(requestInfo.getOperatorKcId(), request));
    } catch (Exception ex) {
      log.error(ex.getMessage());
      return false;
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

  private boolean getUserInfo(HttpServletRequest request) {
    String authorization = request.getHeader(HeaderEnum.HEADER_AUTHORIZATION.getValue());
    if (StringUtils.isBlank(authorization)) {
      return false;
    }
    requestInfo.setAuthorization(authorization);

    String token = authorization.split(" ")[1];
    if (authorization.toLowerCase().contains("basic")) {
      requestInfo.setBasicAuth(true);
      return true;
    }
    GetUserInfoRequest getUserInfoRequest = new GetUserInfoRequest();
    getUserInfoRequest.setToken(token);

    GetUserInfoResponse getUserInfoResponse = authenService.getUserInfo(getUserInfoRequest);
//    operatorKcId = request.getHeader(FieldConstant.OPERATOR_KC_ID);
//    operatorLoginId = request.getHeader(FieldConstant.OPERATOR_LOGIN_ID);
//    operatorName = request.getHeader(FieldConstant.OPERATOR_NAME);
    requestInfo.setOperatorKcId(getUserInfoResponse.getSub());
    requestInfo.setOperatorLoginId(getUserInfoResponse.getName());
    requestInfo.setOperatorName(getUserInfoResponse.getPreferredUsername());

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

package com.yody.common.core;

import com.yody.common.core.dto.Result;
import com.yody.common.core.exception.BaseException;
import com.yody.common.core.exception.InternalServerException;
import com.yody.common.enums.CommonResponseCode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {

  private ObjectError error;
  private ObjectError error1;

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Result> handleException(Exception ex) {
    Result response = new Result();
    ex.printStackTrace();
    if (ex instanceof BaseException) {
      logger.error(ex.getMessage());
      response.setErrors(Arrays.asList(ex.getMessage()));
      response.setCode(((BaseException) ex).getCode());
    }else if(ex instanceof HttpClientErrorException ){
      response.setErrors(Arrays.asList(CommonResponseCode.UNAUTHORIZE.getDisplayName()));
      response.setCode(CommonResponseCode.UNAUTHORIZE.getValue());
    }
    else {
      logger.error(ex.getMessage());
      InternalServerException internalErr = new InternalServerException();
      response.setCode(internalErr.getCode());
      response.setErrors(Arrays.asList(internalErr.getMessage()));
    }
    response.setResponseTime(new Timestamp(System.currentTimeMillis()));
    return new ResponseEntity<Result>(response, HttpStatus.OK);
  }

  // 400
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status,
      final WebRequest request) {
    List<String> errors = new ArrayList<>();
    Iterator var6 = ex.getBindingResult().getFieldErrors().iterator();

    while(var6.hasNext()) {
      FieldError error = (FieldError)var6.next();
      errors.add(error.getField() + ": "+ error.getDefaultMessage());
    }

    var6 = ex.getBindingResult().getGlobalErrors().iterator();

    while(var6.hasNext()) {
      error = (ObjectError)var6.next();
      errors.add(error.getDefaultMessage());
    }

    Result result = this.setupResult();
    result.setErrors(errors);
    result.setCode(CommonResponseCode.BAD_REQUEST.getValue());
    ex.printStackTrace();
    return new ResponseEntity(result, new HttpHeaders(), HttpStatus.OK);
  }

  @Override
  protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
    final List<String> errors = new ArrayList<String>();
    for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.add(error.getField() + ": " + error.getDefaultMessage());
    }
    for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
      errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
    }
    Result result = setupResult();
    result.setErrors(errors);
    ex.printStackTrace();
    // result.setMessage(ex.getLocalizedMessage());
    result.setCode(CommonResponseCode.BAD_REQUEST.getValue());
    return handleExceptionInternal(ex, result, headers, HttpStatus.OK, request);
  }

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
    final String error = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type " + ex.getRequiredType();
    Result result = setupResult();
    result.setErrors(Arrays.asList(error));
    ex.printStackTrace();
    result.setCode(CommonResponseCode.BAD_REQUEST.getValue());
    return new ResponseEntity<Object>(result, new HttpHeaders(), HttpStatus.OK);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex, final HttpHeaders headers, final HttpStatus status,
      final WebRequest request) {
    final String error = ex.getRequestPartName() + " part is missing";
    Result result = setupResult();
    result.setErrors(Arrays.asList(error));
    ex.printStackTrace();
    result.setCode(CommonResponseCode.BAD_REQUEST.getValue());
    return new ResponseEntity<Object>(result, new HttpHeaders(), HttpStatus.OK);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(final MissingServletRequestParameterException ex, final HttpHeaders headers, final HttpStatus status,
      final WebRequest request) {
    final String error = ex.getParameterName() + " parameter is missing";
    Result result = setupResult();
    result.setErrors(Arrays.asList(error));
    ex.printStackTrace();
    result.setCode(CommonResponseCode.BAD_REQUEST.getValue());
    return new ResponseEntity<Object>(result, new HttpHeaders(), HttpStatus.OK);
  }

  @ExceptionHandler({MethodArgumentTypeMismatchException.class})
  public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex, final WebRequest request) {
    final String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
    Result result = setupResult();
    result.setErrors(Arrays.asList(error));
    result.setMessage(ex.getLocalizedMessage());
    result.setCode(CommonResponseCode.BAD_REQUEST.getValue());
    return new ResponseEntity<Object>(result, new HttpHeaders(), HttpStatus.OK);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    final String error = "Message not readable: " + ex.getMessage();
    Result result = setupResult();
    result.setMessage(CommonResponseCode.BAD_REQUEST.getDisplayName());
    result.setErrors(Arrays.asList(error));
    result.setCode(CommonResponseCode.BAD_REQUEST.getValue());
    return new ResponseEntity<Object>(result, new HttpHeaders(), HttpStatus.OK);
  }

  // 404
  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
    final String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
    Result result = setupResult();
    result.setErrors(Arrays.asList(error));
    result.setCode(CommonResponseCode.NOT_FOUND.getValue());
    return new ResponseEntity<Object>(result, new HttpHeaders(), HttpStatus.OK);
  }

  // 405
  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException ex, final HttpHeaders headers, final HttpStatus status,
      final WebRequest request) {
    final StringBuilder builder = new StringBuilder();
    builder.append(ex.getMethod());
    builder.append(" method is not supported for this request. Supported methods are ");
    ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

    Result result = setupResult();
    result.setErrors(Arrays.asList(builder.toString()));
    result.setCode(CommonResponseCode.BAD_REQUEST.getValue());
    return new ResponseEntity<Object>(result, new HttpHeaders(), HttpStatus.OK);
  }

  // 415
  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex, final HttpHeaders headers, final HttpStatus status,
      final WebRequest request) {
    final StringBuilder builder = new StringBuilder();
    builder.append(ex.getContentType());
    builder.append(" media type is not supported. Supported media types are ");
    ex.getSupportedMediaTypes().forEach(t -> builder.append(t + " "));

    Result result = setupResult();
    result.setErrors(Arrays.asList(builder.toString()));
    result.setCode(CommonResponseCode.BAD_REQUEST.getValue());
    return new ResponseEntity<Object>(result, new HttpHeaders(), HttpStatus.OK);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
    Result result = setupResult();
    if (ex instanceof BaseException) {
      this.logger.error(ex.getMessage());
      result.setErrors(Arrays.asList(ex.getMessage()));
      result.setCode(((BaseException)ex).getCode());
    } else if (ex instanceof HttpClientErrorException) {
      result.setErrors(Arrays.asList(CommonResponseCode.UNAUTHORIZE.getDisplayName()));
      result.setCode(CommonResponseCode.UNAUTHORIZE.getValue());
    } else {
      final String error = "Exception Internal: " + ex.getMessage();
      result.setMessage(CommonResponseCode.INTERNAL_ERROR.getDisplayName());
      result.setErrors(Arrays.asList(error));
      result.setCode(CommonResponseCode.INTERNAL_ERROR.getValue());
    }
    return new ResponseEntity<Object>(result, new HttpHeaders(), HttpStatus.OK);
  }

  protected Result setupResult() {
    Result result = new Result();
    result.setResponseTime(new Timestamp(System.currentTimeMillis()));
    return result;
  }
}

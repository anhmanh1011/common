package com.yody.common.core;

import com.yody.common.core.dto.Result;
import com.yody.common.core.exception.BadRequestException;
import com.yody.common.core.exception.BaseException;
import com.yody.common.core.exception.InternalServerException;
import java.sql.Timestamp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result> handleException(Exception ex) {
        Result response = new Result();

        if (ex instanceof BaseException) {
            logger.error(ex.getMessage());
            response.setMessage(ex.getMessage());
            response.setCode(((BaseException) ex).getCode());
        } else if (ex instanceof MethodArgumentTypeMismatchException
            || ex instanceof HttpRequestMethodNotSupportedException
            || ex instanceof HttpMediaTypeNotSupportedException
            || ex instanceof TypeMismatchException) {
            logger.error(ex.getMessage());
            BadRequestException badRequestErr = new BadRequestException();
            response.setCode(badRequestErr.getCode());
            response.setMessage(badRequestErr.getMessage());
        } else {
            logger.error(ex.getMessage());
            InternalServerException internalErr = new InternalServerException();
            response.setCode(internalErr.getCode());
            response.setMessage(internalErr.getMessage());
        }
        response.setResponseTime(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<Result>(response, HttpStatus.OK);
    }
}

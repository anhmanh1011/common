package com.yody.common.core;

import com.yody.common.core.dto.Result;
import com.yody.common.core.exception.BaseException;
import com.yody.common.core.exception.InternalServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
        } else {
            logger.error(ex.getMessage());
            InternalServerException internalError = new InternalServerException();
            response.setCode(internalError.getCode());
            response.setMessage(internalError.getMessage());
        }
        return new ResponseEntity<Result>(response, HttpStatus.OK);
    }
}

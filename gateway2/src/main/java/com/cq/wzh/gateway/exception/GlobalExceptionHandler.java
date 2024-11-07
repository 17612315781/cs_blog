package com.cq.wzh.gateway.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: 王振华
 * @Date: 2024/9/19 0019 17:29
 * @Description:
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseException responseExceptionHandler(Exception e){
        ResponseException exception=new ResponseException(e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.toString());

        return exception;
    }
    @ExceptionHandler(MyException.class)
    @ResponseBody
    public ResponseException responseMyExceptionHandler(MyException e){
        ResponseException exception=new ResponseException(e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.toString());

        return exception;
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
   // @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseException responseMethodExceptionHandler(MethodArgumentNotValidException e){
        ResponseException exception=new ResponseException(e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.toString());

        return exception;
    }
}

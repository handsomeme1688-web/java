package com.zoee.day4repeat.exception;


import com.zoee.day4repeat.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserException.class)
    public Result handleException(UserException e){
        return Result.error("服务器错误");
    }
}

package com.zoee.day4.exception;

import com.zoee.day4.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.PublicKey;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public Result ex(UserException e){
        e.printStackTrace();
        return Result.error(e.getMessage());
    }
}

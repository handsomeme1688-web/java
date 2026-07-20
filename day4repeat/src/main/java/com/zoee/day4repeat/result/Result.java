package com.zoee.day4repeat.result;

import lombok.Data;

@Data
public class Result<T> {
    private int code;
    private String message;
    private T data;

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>();
        result.code=1;
        result.message="success";
        result.data=data    ;
        return result;
    }

    public static <T> Result<T> error(String message){
        Result<T> result = new Result<>();
        result.code=0;
        result.message=message;
        return result;
    }
}

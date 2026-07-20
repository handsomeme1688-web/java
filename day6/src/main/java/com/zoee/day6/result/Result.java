package com.zoee.day6.result;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success(T data) {
        Result<T> r=new Result<T>();
        r.setCode(1);
        r.setMessage("success");
        r.setData(data);
        return r;
    }

    // 为什么这里的参数不是data而是String message？
    public static <T> Result<T> error(String message){
        Result<T> r = new Result<>();
        r.setCode(0);
        r.setMessage(message);
        return r;
    }
}

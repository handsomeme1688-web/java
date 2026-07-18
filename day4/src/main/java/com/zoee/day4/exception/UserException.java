package com.zoee.day4.exception;

public class UserException extends RuntimeException {
    // 构造方法，和类同名 + 没有返回值类型（连 void 都没有）= 构造方法
    public UserException(String message) {
        super(message);
    }
}

package com.zoee.day4.result;


import lombok.Data;

@Data
public class Result<T> {// <T> 泛型占位符：data 到底装什么，用的时候再定
    private Integer code;   // 1=成功 0=失败 —— 前后端约定的暗号
    private String msg; // 给人看的提示
    private T data; // 真正的货。T 让它能装 String、User、List 任何东西

    /**
     * 前面的 <T> 是“声明/定义泛型”，后面的 Result<T> 才是“使用泛型作为返回值类型”。
     * 因为这是一个 static（静态）方法，所以它不能访问类的泛型 <T>。类的泛型，本质上其实是“对象的泛型”。
     * 只有当 new 对象的时候，T 才有具体的实体和物理意义
     *
     * 静态方法是属于类的，它在类还没有被实例化的时候就能直接通过 Result.success(...) 被调用。
     * 而类上的 <T> 必须等到 new Result<String>() 这样创建对象时才能确定。
     * 所以，静态方法根本无法借用类上面定义的 <T>。
     * 为了让这个静态方法能够独立使用泛型，它必须自己宣布独立。
     * @param data
     * @return
     * @param <T>
     */
    public static <T> Result<T> success(T data) {
        Result<T> r = new Result<>(); // 造一个空信封
        r.code = 1; r.msg = "success"; r.data = data;   // 填三个字段
        return r;// 递出去
    }

    public static <T> Result<T> error(String msg) {
        Result<T> r = new Result<>();
        r.code = 0; r.msg = msg;
        return r;
    }
}

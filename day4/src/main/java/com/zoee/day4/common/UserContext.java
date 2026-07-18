package com.zoee.day4.common;

public class UserContext {
    private static final ThreadLocal<Long> User_ID = new ThreadLocal<>();

    public static void setUserId(Long id){
        User_ID.set(id);}
    public static Long getUserId(){
        return User_ID.get();
    }
    public static void remove(){
        User_ID.remove();
    }
}

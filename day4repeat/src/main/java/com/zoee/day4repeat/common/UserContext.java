package com.zoee.day4repeat.common;

public class UserContext {
    private static final ThreadLocal<Long> User_ID = new ThreadLocal<>();
    public static void setUser_ID(Long id){
        User_ID.set(id);
    }
    public static Long getUser_ID(){
        return User_ID.get();
    }

    public static void remove(){
        User_ID.remove();
    }
}

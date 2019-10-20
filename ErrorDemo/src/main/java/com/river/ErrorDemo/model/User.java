package com.river.ErrorDemo.model;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2019-10-20 21:18
 **/
public class User {
    private static String USER_ID = getUserId();

    public User(String id){
        this.USER_ID = id;
    }
    private static String getUserId() {
//        throw new RuntimeException("UserId Not found");
        return "ok";
    }
}

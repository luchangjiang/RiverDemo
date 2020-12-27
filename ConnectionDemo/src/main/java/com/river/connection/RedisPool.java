package com.river.connection;

import com.river.connection.util.RedisUtil;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-09-11 21:48
 **/
public class RedisPool {
    public static void main(String[] args) {
        RedisUtil.set("lucj", "good morning");

        String lucj = RedisUtil.get("lucj").toString();
        System.out.println(lucj);
    }
}

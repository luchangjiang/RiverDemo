package com.river.connection.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-09-11 22:32
 **/
@Component
public class RedisUtil {
    @Autowired
    private static RedisTemplate redisTemplate;


    public static void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public static Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}

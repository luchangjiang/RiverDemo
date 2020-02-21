package com.river.RedisQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public String sendMessage1(String name) {
        try {
            redisTemplate.convertAndSend("TOPIC1", name);
            return "消息发送成功了";

        } catch (Exception e) {
            e.printStackTrace();
            return "消息发送失败了";
        }
    }

    public String sendMessage2(String name) {
        try {
            redisTemplate.convertAndSend("TOPIC2", name);
            return "消息发送成功了";

        } catch (Exception e) {
            e.printStackTrace();
            return "消息发送失败了";
        }
    }
}

package com.river.RedisDemo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2019-07-30 15:30
 **/
@RestController
@RequestMapping("/hash")
public class HashAction {
    @Resource(name = "selfRedisTemplate")
    private RedisTemplate redisTemplate;

    private String HASH_KEY = "river_hash";
//    public RedisTemplate redisTemplate = null;

    @GetMapping("/do")
    public String doTest() {
        /*ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-redis.xml");
        redisTemplate = applicationContext.getBean(RedisTemplate.class);*/

        Map<String, String> map = new HashMap<>();
        map.put("filed1", "value1");
        map.put("filed2", "value2");
        //相当于hmset
        redisTemplate.opsForHash().putAll(HASH_KEY, map);
        //相当于hset
        redisTemplate.opsForHash().put(HASH_KEY, "filed3", "10");
        //相当于hexists key filed //是否存在
        boolean bool = redisTemplate.opsForHash().hasKey(HASH_KEY, "filed3");
        System.out.println(bool);
        //相当于hgetall {filed1=value1, filed2=value2, filed3=10}获取所有hash的键=值
        Map map1 = redisTemplate.opsForHash().entries(HASH_KEY);
        System.out.println(map1);
        //hincrby //加5  如果是原数据是float类型会异常
//        redisTemplate.opsForHash().increment(key, "filed3", 5d);
        pring("filed3");
        //hincrbyfloat 2.3
//        redisTemplate.opsForHash().increment(key, "filed3", 2.2d);
//        pring("filed3");
        //hvals [value1, value2, 17.199999999999999] 获取所有的value
        System.out.println(redisTemplate.opsForHash().values(HASH_KEY));
        //hkeys [filed1, filed2, filed3] //获取所有的键
        System.out.println(redisTemplate.opsForHash().keys(HASH_KEY));
        List<String> list = new ArrayList<>();
        list.add("filed1");
        list.add("filed2");
        //hmget  [value1, value2] // 获取对于的键  值 没有就返回空
        System.out.println(redisTemplate.opsForHash().multiGet(HASH_KEY, list));
        //hsetnx 不存在的时候才会设置进入true   否则返回false
        System.out.println(redisTemplate.opsForHash().putIfAbsent(HASH_KEY,"filed4", "value4"));
        //hdel 返回删除个数
        System.out.println(redisTemplate.opsForHash().delete(HASH_KEY,"filed1","filed2","filed6"));

        return "success";
    }

    public void pring(String filed) {
        System.out.println(redisTemplate.opsForHash().get(HASH_KEY, filed));
    }
}

package com.river.RedisDemo;

import com.river.RedisDemo.rest.HashAction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2019-07-30 14:29
 **/
public class RankInitTest {

    private Random random;
    private RestTemplate restTemplate;

    @Autowired
    private HashAction testHash;

    @Before
    public void init() {
        random = new Random();
        restTemplate = new RestTemplate();
    }

    private int genUserId() {
        return random.nextInt(1024);
    }

    private double genScore() {
        return random.nextDouble() * 100;
    }

    @Test
    public void testInitRank() {
        for (int i = 0; i < 30; i++) {
            restTemplate.getForObject("http://localhost:7038/rank/update?userId=" + genUserId() + "&score=" + genScore(),
                    String.class);
        }
    }

    @Test
    public void testHash(){
        String result = restTemplate.getForObject("http://localhost:7038/hash/do", String.class);
        Assert.assertTrue("success".equals(result));
    }
}

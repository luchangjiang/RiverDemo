package com.river.connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class ConnectionDemoApplication {
	@Autowired
	private static RedisTemplate redisTemplate;

	public static void main(String[] args) {

		SpringApplication.run(ConnectionDemoApplication.class, args);

		redisTemplate.opsForValue().set("lucj", "good morning");

		String lucj = redisTemplate.opsForValue().get("lucj").toString();
		System.out.println(lucj);
	}




}

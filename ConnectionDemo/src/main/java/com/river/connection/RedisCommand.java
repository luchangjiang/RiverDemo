package com.river.connection;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisFuture;
import com.lambdaworks.redis.RedisURI;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.async.RedisAsyncCommands;
import com.lambdaworks.redis.api.sync.RedisCommands;

import java.util.concurrent.ExecutionException;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-09-11 21:00
 **/
public class RedisCommand {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RedisURI redisURI1 = RedisURI.builder().withHost("127.0.0.1").withPort(6379).withPassword("123456").build();

        RedisURI redisURI2 = RedisURI.Builder.redis("127.0.0.1").withPort(6379).withPassword("123456").build();

//        RedisURI redisURI3 = new RedisURI("127.0.0.1", 6379, Duration.ofMillis(-1));

        RedisClient redisClient = RedisClient.create(redisURI1);

        StatefulRedisConnection connection = redisClient.connect();

        RedisCommands<String, String> commands = connection.sync();
        System.out.println(commands.get("name"));

        commands.set("lucj", "luchangjiang");

        System.out.println("lucj: " + commands.get("lucj"));

        RedisAsyncCommands<String, String> asyncCommands = connection.async();
        RedisFuture<String> rs = asyncCommands.get("wk");

//        rs.thenAccept(System.out::println);


        /*while (!rs.isDone()){
            System.out.println(rs.get());
        }*/

        connection.close();
        redisClient.shutdown();
    }
}

package com.river.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 * https://blog.csdn.net/ffj0721/article/details/82630134
 */
@SpringBootApplication
public class WebSocketEndPoint
{
    public static void main( String[] args )
    {
        SpringApplication.run(WebSocketEndPoint.class, args);
        System.out.println( "Hello World!" );
    }
}

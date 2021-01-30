package com.river.mybatis.sensitive;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    @Bean
    public SensitivePlugin sensitivePlugin(){
        return new SensitivePlugin();
    }
}

package com.river.jsonDemo.config;

import com.river.jsonDemo.service.JsonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2019-07-30 11:12
 **/
@Configuration
@Order(-1)
public class JsonConfig {

    @Bean
    public JsonService beanJsonService(){
        return new JsonService();
    }
}

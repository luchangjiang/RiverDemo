package com.river.starterDemo.config;

import com.river.starterDemo.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-05-17 21:18
 **/
@Configuration
@EnableConfigurationProperties(PhoneProperties.class)
@ConditionalOnClass(PhoneService.class)
@ConditionalOnProperty(prefix = "spring.phone",value = "enable", matchIfMissing = true)
public class PhoneAutoConfiguration {

    @Autowired
    PhoneProperties phoneProperties;

    @Bean
    @ConditionalOnMissingBean(PhoneService.class)
    public PhoneService getPhoneService(){
        return new PhoneService(phoneProperties);
    }
}

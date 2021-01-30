package com.river.configuration;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-10-21 22:49
 **/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import static java.lang.System.out;

@SpringBootApplication
public class ConfigurationApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(ConfigurationApplication.class, args);

        for (String name : applicationContext.getBeanDefinitionNames()) {
            out.println(name);
        }
    }
}

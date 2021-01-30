package com.river.configuration.config;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-10-21 22:45
 **/
import com.river.configuration.pojo.Person;
import com.river.configuration.pojo.Student;
import org.springframework.context.annotation.*;

@Configuration
@Import({Student.class, MyImportSelector.class, MyImportBeanDefinitionRegistar.class})
public class MyConfig {
    @Bean
    public Person person(){
        return new Person("Michael", 19);
    }
}


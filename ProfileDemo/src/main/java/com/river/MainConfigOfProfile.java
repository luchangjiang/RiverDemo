package com.river;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-05-25 22:56
 **/

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Profile:
 * 		Spring为我们提供的可以根据当前环境，动态的激活和切换一系列组件的功能；
 *
 * 开发环境develop、测试环境test、生产环境master
 * 数据源：(/dev) (/test) (/master)
 *
 * @Profile:指定组件在哪个环境的情况下才能被注册到容器中，不指定，任何环境下都能注册这个组件
 *
 * 1) 加了环境标识的bean，只有这个环境被激活的时候才能注册到容器中。默认是default环境
 * 2) 写在配置类上，只有是指定的环境的时候，整个配置类里面的所有配置才能开始生效
 *
 */
@PropertySource("classpath:/dbconfig.properties")
@Configuration
public class MainConfigOfProfile implements EmbeddedValueResolverAware{

    @Value("${db.user}")
    private String user;

    private String driverClass;

    @Profile("default")
    @Bean("test")
    public DataSource testDataSource(@Value("${db.password}")String password) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Profile("dev")
    @Bean("dev")
    public DataSource devDataSource(@Value("${db.password}")String password) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Profile("master")
    @Bean("master")
    public DataSource masterDataSource(@Value("${db.password}")String password) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        String driverClass = resolver.resolveStringValue("${db.driverClass}");
        this.driverClass = driverClass;
    }

}
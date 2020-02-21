package com.river.LindSecurity.config;

import com.river.LindSecurity.authentication.LindAuthenticationProvider;
import com.river.LindSecurity.authentication.LindUserNameAuthenticationFilter;
import com.river.LindSecurity.handler.LindAuthenticationFailHandler;
import com.river.LindSecurity.handler.LindAuthenticationSuccessHandler;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

/**
 * @author ：River
 * @date ：Created in 7/25/2019 5:44 PM
 * @description：
 * @modified By：
 * @version: $
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    LindAuthenticationSuccessHandler lindAuthenticationSuccessHandler;

    @Autowired
    LindAuthenticationFailHandler lindAuthenticationFailHandler;

    @Autowired
    LindAuthenticationProvider lindAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/index").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")//按路由授权
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/hello")//默认登录成功后跳转的页面
                .successHandler(lindAuthenticationSuccessHandler)
                .failureHandler(lindAuthenticationFailHandler)
                .permitAll()
                .and()
                .addFilterAt(lindAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class).authorizeRequests().and()
                .logout()
                .permitAll();
    }

    /**
     * 自定义的Filter.
     */
    @Bean
    LindUserNameAuthenticationFilter lindAuthenticationFilter() {
        LindUserNameAuthenticationFilter phoneAuthenticationFilter = new LindUserNameAuthenticationFilter();
        ProviderManager providerManager =
                new ProviderManager(Collections.singletonList(lindAuthenticationProvider));
        phoneAuthenticationFilter.setAuthenticationManager(providerManager);
        phoneAuthenticationFilter.setAuthenticationSuccessHandler(lindAuthenticationSuccessHandler);
        phoneAuthenticationFilter.setAuthenticationFailureHandler(lindAuthenticationFailHandler);
        return phoneAuthenticationFilter;
    }

    /*@Bean
    public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter(){
        UsernamePasswordAuthenticationFilter usernameAuthenticationFilter = new UsernamePasswordAuthenticationFilter();
        ProviderManager providerManager =
                new ProviderManager(Collections.singletonList(lindAuthenticationProvider));
        usernameAuthenticationFilter.setAuthenticationManager(providerManager);
        usernameAuthenticationFilter.setAuthenticationSuccessHandler(lindAuthenticationSuccessHandler);
        usernameAuthenticationFilter.setAuthenticationFailureHandler(lindAuthenticationFailHandler);
        return usernameAuthenticationFilter;
    }*/

    /**
     * 密码生成策略.
     *
     * @return
     */
    @Bean
    public LindAuthenticationSuccessHandler lindAuthenticationSuccessHandler() {
        return new LindAuthenticationSuccessHandler();
    }


    /**
     * 密码生成策略.
     *
     * @return
     */
    @Bean
    public LindAuthenticationFailHandler lindAuthenticationFailHandler() {
        return new LindAuthenticationFailHandler();
    }


    /**
     * 密码生成策略.
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}

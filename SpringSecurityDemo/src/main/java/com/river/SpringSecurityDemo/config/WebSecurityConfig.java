package com.river.SpringSecurityDemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.river.SpringSecurityDemo.handler.RiverAuthenticationFailHandler;
import com.river.SpringSecurityDemo.handler.RiverAuthenticationSuccessHandler;
import com.river.SpringSecurityDemo.service.MyUserDetailsService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author ：River
 * @date ：Created in 7/24/2019 5:44 PM
 * @description：
 * @modified By：
 * @version: $
 */

@Primary
@Order(90)
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/token/login")
                .loginProcessingUrl("/token/form")
                .and()
                .authorizeRequests()
                .antMatchers("/token/**",
                        "/oauth/**",
                        "/param/**",
                        "/actuator/**",
                        "/mobile/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .apply(riverSecurityConfigurer());
    }

    @Bean
    @Override
    @SneakyThrows
    public AuthenticationManager authenticationManagerBean() {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationSuccessHandler riverAuthenticationSuccessHandler() {
        return RiverAuthenticationSuccessHandler.builder()
                .objectMapper(objectMapper)
                .passwordEncoder(passwordEncoder())
                .build();
    }

    @Bean
    public AuthenticationFailureHandler riverAuthenticationFailureHandler() {
        return RiverAuthenticationFailHandler.builder().build();
    }

    @Bean
    public RiverSecurityConfigurer riverSecurityConfigurer() {
        return RiverSecurityConfigurer.builder()
                .passwordEncoder(passwordEncoder())
                .myUserDetailsService(myUserDetailService())
                .riverAuthenticationSuccessHandler(riverAuthenticationSuccessHandler())
                .riverAuthenticationFailHandler(riverAuthenticationFailureHandler())
                .build();
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

    /**
     * 密码生成策略.
     *
     * @return
     */
    @Bean
    public MyUserDetailsService myUserDetailService() {
        return new MyUserDetailsService(passwordEncoder());
    }
}

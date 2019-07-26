package com.river.SpringSecurityDemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.river.SpringSecurityDemo.authentication.RiverAuthenticationProvider;
import com.river.SpringSecurityDemo.authentication.RiverUserNameAuthenticationFilter;
import com.river.SpringSecurityDemo.handler.RiverAuthenticationFailHandler;
import com.river.SpringSecurityDemo.handler.RiverAuthenticationSuccessHandler;
import com.river.SpringSecurityDemo.service.MyUserDetailsService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
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
 * @date ：Created in 7/24/2019 5:44 PM
 * @description：
 * @modified By：
 * @version: $
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    RiverAuthenticationSuccessHandler riverAuthenticationSuccessHandler;

    @Autowired
    RiverAuthenticationFailHandler riverAuthenticationFailHandler;

    @Autowired
    RiverAuthenticationProvider riverAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")//按路由授权
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/hello")//默认登录成功后跳转的页面
                .successHandler(riverAuthenticationSuccessHandler)
                .failureHandler(riverAuthenticationFailHandler)
                .permitAll()
                .and()
                .addFilterAt(riverAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class).authorizeRequests().and()
                .logout()
                .permitAll();
    }

    @Bean
    @Override
    @SneakyThrows
    public AuthenticationManager authenticationManagerBean() {
        return super.authenticationManagerBean();
    }

    /**
     * 自定义的Filter.
     */
    @Bean
    RiverUserNameAuthenticationFilter riverAuthenticationFilter() {
        RiverUserNameAuthenticationFilter phoneAuthenticationFilter = new RiverUserNameAuthenticationFilter();
        ProviderManager providerManager =
                new ProviderManager(Collections.singletonList(riverAuthenticationProvider));
        phoneAuthenticationFilter.setAuthenticationManager(providerManager);
        phoneAuthenticationFilter.setAuthenticationSuccessHandler(riverAuthenticationSuccessHandler);
        phoneAuthenticationFilter.setAuthenticationFailureHandler(riverAuthenticationFailHandler);
        return phoneAuthenticationFilter;
    }

    @Bean
    public RiverSecurityConfigurer riverSecurityConfigurer() {
        return new RiverSecurityConfigurer();
    }

    /**
     * 验证成功处理.
     *
     * @return
     */
    @Bean
    public RiverAuthenticationSuccessHandler lindAuthenticationSuccessHandler() {
        return new RiverAuthenticationSuccessHandler();
    }

    /**
     * 验证失败处理.
     *
     * @return
     */
    @Bean
    public RiverAuthenticationFailHandler lindAuthenticationFailHandler() {
        return new RiverAuthenticationFailHandler();
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
     * 用户服务.
     *
     * @return
     */
    @Bean
    public MyUserDetailsService myUserDetailService() {
        return new MyUserDetailsService();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}

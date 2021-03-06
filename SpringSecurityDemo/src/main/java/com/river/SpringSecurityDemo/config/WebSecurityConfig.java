package com.river.SpringSecurityDemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.river.SpringSecurityDemo.authentication.RiverAuthenticationProvider;
import com.river.SpringSecurityDemo.handler.RiverAuthenticationFailHandler;
import com.river.SpringSecurityDemo.handler.RiverAuthenticationSuccessHandler;
import com.river.SpringSecurityDemo.service.RiverClientDetailsService;
import com.river.SpringSecurityDemo.service.RiverUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;
import java.util.Collection;

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
@AllArgsConstructor
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
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
    public RiverSecurityConfigurer riverSecurityConfigurer() {
        return RiverSecurityConfigurer.builder()
                .riverUserDetailsService(myUserDetailService())
                .riverAuthenticationSuccessHandler(riverAuthenticationSuccessHandler())
                .riverAuthenticationFailHandler(riverAuthenticationFailHandler())
                .objectMapper(objectMapper())
                .passwordEncoder(passwordEncoder())
                .build();
    }

    /**
     * 验证成功处理.
     *
     * @return
     */
    @Bean
    public AuthenticationSuccessHandler riverAuthenticationSuccessHandler() {
        return new RiverAuthenticationSuccessHandler();
        /*return RiverAuthenticationSuccessHandler.builder()
                .objectMapper(objectMapper())
                .clientDetailsService(riverClientDetailsService)
                .passwordEncoder(passwordEncoder())
                .build();*/
    }

    /**
     * 验证失败处理.
     *
     * @return
     */
    @Bean
    public RiverAuthenticationFailHandler riverAuthenticationFailHandler() {
        return new RiverAuthenticationFailHandler();
    }


    @Bean
    @Override
    @SneakyThrows
    public AuthenticationManager authenticationManagerBean() {
        return super.authenticationManagerBean();
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
    public RiverUserDetailsService myUserDetailService() {
        return new RiverUserDetailsService();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}

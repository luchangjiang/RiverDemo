package com.river.SpringSecurityDemo.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-10-03 18:10
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        //直接写死数据信息，可以在这里获取数据库的信息并进行验证

        UserDetails userDetails  = User.withUsername(s).password(new BCryptPasswordEncoder().encode("123456"))
                .authorities("bxsheng").build();
        return userDetails;
    }
}


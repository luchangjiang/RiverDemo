package com.river.SpringSecurityDemo.service;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：River
 * @date ：Created in 7/24/2019 5:43 PM
 * @description：
 * @modified By：
 * @version: $
 */
public class RiverUserDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    /*
      设置用户和角色需要注意：
      1. commaSeparatedStringToAuthorityList放入角色时需要加前缀ROLE_，而在controller使用时不需要加ROLE_前缀
      2. 放入的是权限时，不能加ROLE_前缀，hasAuthority与放入的权限名称对应即可
    */
        String password = passwordEncoder.encode("123456");

        List<UserDetails> userDetailsList = new ArrayList<>();
        userDetailsList.add(User.builder()
                .username("admin")
                .password(password)
                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("read,ROLE_ADMIN")).build());
        userDetailsList.add(User.builder()
                .username("user")
                .password(password)
                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("read,ROLE_USER"))
                .build());

        //获取用户
        return userDetailsList.stream()
                .filter(o -> o.getUsername().equals(name))
                .findFirst()
                .orElse(null);

    }
}

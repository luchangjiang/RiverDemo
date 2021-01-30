package com.river.SpringSecurityDemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.river.SpringSecurityDemo.pojo.entity.User;
import com.river.SpringSecurityDemo.service.UserService;
import com.river.SpringSecurityDemo.util.JwtUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-10-03 17:10
 **/
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/register")
    public String registerUser(@RequestBody Map<String,String> registerUser){
        User user = new User();
        user.setUsername(registerUser.get("username"));
        user.setPassword(bCryptPasswordEncoder.encode(registerUser.get("password")));
        user.setRole("ROLE_USER");
        userService.save(user);
        return "success";
    }

    @PostMapping("/refreshToken")
    public String refreshToken(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getPrincipal().toString();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        //创建jwt信息
        String token = JwtUtils.createToken(username, userDetails.getAuthorities().toString());
        return token;
    }


}


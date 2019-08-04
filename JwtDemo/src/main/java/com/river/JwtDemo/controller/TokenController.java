package com.river.JwtDemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.river.JwtDemo.config.JwtConfig;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2019-08-03 22:21
 **/
@RestController
public class TokenController {
    @Resource
    private JwtConfig jwtConfig ;
    // 拦截器直接放行，返回Token
    @PostMapping("/login")
    public Map<String,String> login (@RequestParam("username") String username,
                                     @RequestParam("password") String password){
        Map<String,String> result = new HashMap<>() ;

        Map<String,String> info = new HashMap<>();
        info.put("username", username);
        info.put("password", password);

        // 省略数据源校验
        String token = jwtConfig.getToken(JSONObject.toJSONString(info)) ;
        if (!StringUtils.isEmpty(token)) {
            result.put("token",token) ;
        }
        result.put("username",username) ;
        return result ;
    }
    // 需要 Token 验证的接口
    @PostMapping("/info")
    public String info (HttpServletRequest request){
        String identityId = request.getAttribute("identityId").toString();
        return identityId ;
    }
}

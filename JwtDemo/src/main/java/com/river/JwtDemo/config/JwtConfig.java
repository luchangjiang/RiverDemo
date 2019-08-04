package com.river.JwtDemo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2019-08-03 22:16
 **/
@ConfigurationProperties(prefix = "config.jwt")
@Component
@Data
public class JwtConfig {
    /*
     * 根据身份ID标识，生成Token
     */
    public String getToken (String identityId){
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(identityId)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /*
     * 获取 Token 中注册信息
     */
    public Claims getTokenClaim (String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /*
     * 获取 Token 中注册信息
     */
    public String getTokenInfo (String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).toString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /*
     * Token 是否过期验证
     */
    public boolean isTokenExpired (Date expirationTime) {
        return expirationTime.before(new Date());
    }
    private String secret;
    private long expire;
    private String header;
    // 省略 GET 和 SET
}

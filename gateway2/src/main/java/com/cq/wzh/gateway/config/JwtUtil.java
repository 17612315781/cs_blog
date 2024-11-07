package com.cq.wzh.gateway.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @Author: 王振华
 * @Date: 2024/9/18 0018 16:00
 * @Description:
 */
@Component
public class JwtUtil {
    private static String SIGNATURE = "token!@#$%^7890";

    /**
     * 生成token
     * @param map //传入payload
     * @return 返回token
     */
    public  String getToken(Map<String,Object> map){
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.ES256, SIGNATURE)//设置加密算法与签名
                .setClaims(map)//添加用户信息(载荷)
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 3600 * 1000))//设置有效期
                .compact();
        return jwt;

    }

    /**
     * @description 获取token中payload
     * @param token
     * @return
     */
    public Claims get(String token){
        Claims data = Jwts.parser()
                .setSigningKey(SIGNATURE)//秘钥
                .parseClaimsJws(token)
                .getBody();//获取自定义的内容
        return data;
    }




}

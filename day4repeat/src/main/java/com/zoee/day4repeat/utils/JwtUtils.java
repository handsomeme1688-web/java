package com.zoee.day4repeat.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static final String signKey = "7xK9pW2mN5bQ8vR1zT4cX6sY9dE2fG5h";
    private static final Long expire = 86400000L;

    //生成令牌
    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,signKey)
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+expire))
                .compact();
    }

    //解析令牌
    public static Claims parseJwt(String jwt){
        return Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)// 千万不能用 parseClaimsJwt
                .getBody();
    }

}

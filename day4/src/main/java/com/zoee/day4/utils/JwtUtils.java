package com.zoee.day4.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Map;



public class JwtUtils {

    private static final String signKey="7xK9pW2mN5bQ8vR1zT4cX6sY9dE2fG5h";
    private static final Long expire=43200000L;

    /**
     * JWT令牌是一串字符串，由三部分组成：头部、载荷、签名。
     * 头部和载荷都是JSON对象，签名是通过将头部和载荷用点连接起来，然后用密钥和HMAC SHA256算法生成的。
     * 生成JWT令牌
     * @param claims
     * @return
     */
    public static String generateJwt(Map<String, Object> claims){
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, signKey)
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
    }


    /**
     * 解析JWT令牌
     * @param jwt
     * @return
     */
    public static Claims parseJwt(String jwt){
        return Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}

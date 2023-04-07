package com.example.websocketdemo1.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtUtils {

    //签名密钥
    private static String signKey = "secretKey";

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)//签名密钥
                .parseClaimsJws(jwt)//Token
                .getBody();
        return claims;
    }
}


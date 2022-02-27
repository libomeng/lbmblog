package com.lbm.api.util;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    private final static String jwtToken = "lbm***(*()*)(*)!@#a";
    private final static String KEY = "userID";

    /**
     * 新建一个Token
     *
     * @param userID
     * @return token
     */
    public static String createToken(Object userID) {
        Map<String, Object> claims = new HashMap<>();
        String id = userID.toString();
        claims.put(KEY, id);
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtToken)//签发算法，秘钥是jwtToken
                .setClaims(claims)   //ToKen Body 内容
                .setIssuedAt(new Date());  //ToKen 签发时间
        String ToKen = jwtBuilder.compact();
        return ToKen;
    }

    public static String createToken(Object userID, Long expired) {
        Map<String, Object> claims = new HashMap<>();
        String id = userID.toString();
        claims.put(KEY, id);
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtToken)//签发算法，秘钥是jwtToken
                .setClaims(claims)   //ToKen Body 内容
                .setIssuedAt(new Date())  //ToKen 签发时间
                .setExpiration(new Date(System.currentTimeMillis() + expired));
        String ToKen = jwtBuilder.compact();
        return ToKen;
    }

    /**
     * token 验证
     *
     * @param token
     * @return
     */
    public static String checkToken(String token) {
        Jwt parse = Jwts.parser().setSigningKey(jwtToken).parse(token);
        Map<String, Object> body = (Map<String, Object>) parse.getBody();
        String id = (String) body.get(KEY);
        return id;
    }
}


package com.lbm.api.util;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    private static String jwtToken ="lbm***(*()*)(*)!@#a";

    /**
     * 新建一个Token
     * @param userID
     * @return token
     */
    public static String createToken(Object userID){

        Map<String,Object> claims =new HashMap<>();
        String id = userID.toString();
        claims.put("userID", id);
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,jwtToken)//签发算法，秘钥是jwtToken
                .setClaims(claims)   //ToKen Body 内容
                .setIssuedAt(new Date())  //ToKen 签发时间
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24));
        String ToKen= jwtBuilder.compact();
        return ToKen;

    }

    /**
     * token 验证
     * @param token
     * @return
     */
    public static Map<String, Object> checkToken(String token){
        Jwt parse = Jwts.parser().setSigningKey(jwtToken).parse(token);
        Map<String,Object> body = (Map<String,Object>)parse.getBody();

        return body;
    }

    public static void main(String[] args) {
        String token="eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MzgxNzkxNTEsInVzZXJJRCI6IjIxMzIxIiwiaWF0IjoxNjM4MTc1NTUxfQ.s-J-6NKlyn1e3l0Dc1DjnDSBu_KTIjyLoi_h-OVHOL4";
        Map<String, Object> stringObjectMap = checkToken(token);
        System.out.println(stringObjectMap);
    }



}

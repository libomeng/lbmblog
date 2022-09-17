package com.lbm.common.uitl;
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
                .setIssuedAt(new Date())//ToKen 签发时间
                .setExpiration(new Date(System.currentTimeMillis()+60*60*1000));//设置token过期时间
        String ToKen = jwtBuilder.compact();
        return ToKen;
    }

    /**
     * 根据传入的map生成token
     * @param map
     * @return
     */
    public static String createToken(Map<String,Object> map) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtToken)//签发算法，秘钥是jwtToken
                .setClaims(map)   //ToKen Body 内容
                .setIssuedAt(new Date());  //ToKen 签发时间
        String ToKen = jwtBuilder.compact();
        return ToKen;
    }

    /**
     * 根据id签发token,并设置过期时间
     * @param userID
     * @param expired
     * @return
     */
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
     * @return 返回id值
     */
    public static String checkToken(String token) throws Exception {
        Jwt parse = Jwts.parser().setSigningKey(jwtToken).parse(token);
        Map<String, Object> body = (Map<String, Object>) parse.getBody();
        String id = (String) body.get(KEY);
        return id;
    }

    /**
     * token验证
     * @param token
     * @return 返回Map
     */
    public static Map checkTokenResMap(String token) {
        Jwt parse = Jwts.parser().setSigningKey(jwtToken).parse(token);
        Map<String, Object> body = (Map<String, Object>) parse.getBody();
        return body;
    }
}


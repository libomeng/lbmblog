package com.lbm.api.util;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public class RedisUtil {

    public static void saveToKen(String token,Integer saveTime){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.opsForValue().set("TOKEN_", token,saveTime, TimeUnit.HOURS);
    }
    public static void saveToKen(String token){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.opsForValue().set("TOKEN_", token,24, TimeUnit.HOURS);
    }

    public static void main(String[] args) {
        saveToKen("123213123");
    }
}



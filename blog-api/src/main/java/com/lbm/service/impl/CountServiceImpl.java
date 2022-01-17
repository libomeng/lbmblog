package com.lbm.service.impl;

import com.lbm.config.RedisKeyConfig;
import com.lbm.controller.CountService;
import com.lbm.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountServiceImpl implements CountService{
    @Autowired
    RedisService redisService;

    @Override
    public Integer addCount() {
        Integer count = redisService.getEntity(RedisKeyConfig.SERVER_COUNT, Integer.class);
        if(count == null){
            count=0;
        }
        count++;
        redisService.setEntity(RedisKeyConfig.SERVER_COUNT,count);
        return count;
    }
}

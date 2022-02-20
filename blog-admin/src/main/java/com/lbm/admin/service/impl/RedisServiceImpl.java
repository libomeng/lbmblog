package com.lbm.admin.service.impl;

import com.lbm.admin.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    RedisTemplate jsonRedisTemplate;
    @Override
    public void removeByHash(Object key) {
        jsonRedisTemplate.delete(key);
    }
}

package com.lbm.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.lbm.admin.entity.dos.SysUserDetails;
import com.lbm.admin.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    RedisTemplate jsonRedisTemplate;
    @Override
    public void removeByHash(Object key) {
        jsonRedisTemplate.delete(key);
    }

    @Override
    public SysUserDetails getSysUserDetails(Object key) {
        return (SysUserDetails) jsonRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void saveSysUserDetails(Object key, SysUserDetails sysUserDetails) {
        jsonRedisTemplate.opsForValue().set(key, sysUserDetails,60,TimeUnit.MINUTES);
    }


}

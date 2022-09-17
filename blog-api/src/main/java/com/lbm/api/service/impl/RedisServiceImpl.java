package com.lbm.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.lbm.common.config.RedisKeyConfig;
import com.lbm.api.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    RedisTemplate jsonRedisTemplate;

    @Override
    public Map getMapByHash(String hash) {
        return jsonRedisTemplate.opsForHash().entries(hash);

    }

    @Override
    public boolean isEmptyMap(String redisKey) {
        return !jsonRedisTemplate.hasKey(redisKey);
    }

    @Override
    public void setMapByHash(String redisKey, Map map) {
        Set<String> set = map.keySet();
        for (String key : set) {
            Object value = map.get(key);
            jsonRedisTemplate.opsForHash().put(redisKey, key, value);
        }
    }


    @Override
    public <T> T getEntity(String key, String id, Class<T> clazz) {
        Object o = jsonRedisTemplate.opsForHash().get(key, id);
        String js = JSON.toJSONString(o);
        return JSON.parseObject(js, clazz);
    }

    @Override
    public <T> T getEntity(String key, Class<T> clazz) {
        Object o = jsonRedisTemplate.opsForValue().get(key);
        String js = JSON.toJSONString(o);
        return JSON.parseObject(js, clazz);
    }

    @Override
    public Object getEntity(String key) {
        return jsonRedisTemplate.opsForValue().get(key);
    }

    @Override
    public Object getEntity(String key, Object hash) {
        Object o = jsonRedisTemplate.opsForHash().get(key,hash);
        return o;
    }


    @Override
    public void setEntity(String key, Object hash, Object obj) {
        jsonRedisTemplate.opsForHash().put(key,hash, obj);
    }

    @Override
    public void setHashTimeout(String key, Object hashKey, Object value, Long time) {
        jsonRedisTemplate.opsForHash().put(key,hashKey,value);
        jsonRedisTemplate.expire(key,time,TimeUnit.MINUTES);
    }

    @Override
    public void setEntity(String key, Object obj){
        jsonRedisTemplate.opsForValue().set(key,obj);
    }
    public void setEntity(String key, Object obj,Long time){
        jsonRedisTemplate.opsForValue().set(key,obj,time,TimeUnit.MINUTES);
    }
    @Override
    public <T> List<T> getListByKey(String key, Object hashKey) {
        List<T> list = (List<T>) jsonRedisTemplate.opsForHash().get(key, hashKey);
        return list;
    }

    @Override
    public void setListByKey(String key, Object hashKey, List list) {
        jsonRedisTemplate.opsForHash().put(key, hashKey, list);
    }

    @Override
    public void saveArticleView( String id) {
        Integer count = this.getArticleView(id);
        jsonRedisTemplate.opsForHash().put(RedisKeyConfig.ARTICLE_VIEW_MAP,id,count+1);
    }
    @Override
    public Integer getArticleView(String id) {
        Integer count = (Integer)jsonRedisTemplate.opsForHash().get(RedisKeyConfig.ARTICLE_VIEW_MAP, id);
        return count;
    }

    @Override
    public void removeMap(String key) {
        jsonRedisTemplate.delete(key);
    }

    @Override
    public void removeValueByHash(String key, Object hash) {
        jsonRedisTemplate.opsForHash().delete(key,hash);
    }

    @Override
    public void removeValue(String key) {
        jsonRedisTemplate.delete(key);
    }
}

package com.lbm.api.service;

import java.util.List;
import java.util.Map;

public interface RedisService {
    Map getMapByHash(String hash);

    /**
     * 判断Map是否为空
     *
     * @param redisKey
     * @return
     */
    boolean isEmptyMap(String redisKey);

    /**
     * 根据Hash保存Map
     *
     * @param redisKey
     * @param map
     */
    void setMapByHash(String redisKey, Map map);

    /**
     * 在Redis中更新文章阅读数
     *
     * @param
     */


    <T> T getEntity(String key, String id, Class<T> clazz);

    <T> T getEntity(String key, Class<T> clazz);

    <T> T getEntity(String key,Object hash);

    <T> T getEntity(String key);
    void setEntity(String key, Object obj);

    void setEntity(String key, Object hash, Object obj);

    void setHashTimeout(String hash, Object key,Object value,Long time);

    <T> List<T> getListByKey(String key, Object hashKey);

    void setListByKey(String key, Object hashKey, List list);

    void saveArticleView(String id);

    Integer getArticleView(String id);

    void removeMap(String articleViewMap);

    void removeValueByHash(String key, Object hash);

    void removeValue(String key);
}

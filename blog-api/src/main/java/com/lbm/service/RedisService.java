package com.lbm.service;

import com.lbm.dao.entity.Article;
import com.lbm.vo.ArticleVo;

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


    <T> T getEntity(String key, Long id, Class<T> clazz);

    <T> T getEntity(String key, Class<T> clazz);

    <T> T getEntity(String key,Object hash);

    <T> T getEntity(String key);

    void setEntity(String key, Long id, Object obj);

    void setEntity(String key, Object hash, Object obj);

    void setEntity(String key, Object obj);

    <T> List<T> getListByKey(String key, Object hashKey);

    void setListByKey(String key, Object hashKey, List list);

    void saveArticleView(Long id);

    Integer getArticleView(Long id);

    void removeMap(String articleViewMap);

    void removeValueByHash(String key, Object hash);

    void removeValue(String key);
}

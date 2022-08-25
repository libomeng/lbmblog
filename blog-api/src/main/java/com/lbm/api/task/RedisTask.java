package com.lbm.api.task;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.lbm.api.common.aop.LogAnnotation;
import com.lbm.common.config.RedisKeyConfig;
import com.lbm.api.dao.entity.Article;
import com.lbm.api.service.ArticleService;
import com.lbm.api.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Map;
import java.util.Set;

@Configuration
@EnableScheduling
/**
 * Redis定时任务
 */
public class RedisTask{

    @Autowired
    RedisTemplate jsonRedisTemplate;

    @Autowired
    ArticleService articleService;

    @Autowired
    RedisService redisService;

    /**
     * 固化文章浏览量数据到数据库
     */
    @LogAnnotation
    @Scheduled(fixedDelay = 5*1000)
    public void updateArticleViewCount(){
        String redisKey = RedisKeyConfig.ARTICLE_VIEW_MAP;
        Map articleViews = jsonRedisTemplate.opsForHash().entries(redisKey);
        Set<String> keys = articleViews.keySet();
        for (String key : keys) {
            Integer viewCount = (Integer)articleViews.get(key);
            LambdaUpdateWrapper<Article> updateWrapper =new LambdaUpdateWrapper();
            updateWrapper.set(Article::getViewCounts, viewCount);
            updateWrapper.eq(Article::getId, Long.parseLong(key));
            articleService.update(updateWrapper);
        }
        //删除最热标签缓存
        redisService.removeValue(RedisKeyConfig.TAG_LIST+"host");
        //删除最热文章缓存
        redisService.removeMap(RedisKeyConfig.LIST_HOT_ARTICLES);
    }
}
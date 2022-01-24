package com.lbm.api.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.lbm.api.dao.entity.Article;
import com.lbm.api.dao.entity.Comment;
import com.lbm.api.dao.mapper.ArticleMapper;
import com.lbm.api.dao.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Async("taskExecutor")
@Component
public class ThreadPoolService {
    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RedisService redisService;


    public void updateArticleViewCount(String id) {
        redisService.saveArticleView(id);

    }


    public void updateArticleCommentCount(String ArticleId) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId, ArticleId);
        Integer count = commentMapper.selectCount(queryWrapper);
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Article::getId, ArticleId);
        Article updateArticle = new Article();
        updateArticle.setCommentCounts(count);
        articleMapper.update(updateArticle, updateWrapper);


    }
}

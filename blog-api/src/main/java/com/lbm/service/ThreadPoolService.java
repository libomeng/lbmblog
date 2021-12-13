package com.lbm.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.lbm.config.RedisKeyConfig;
import com.lbm.dao.entity.Article;
import com.lbm.dao.entity.Comment;
import com.lbm.dao.mapper.ArticleMapper;
import com.lbm.dao.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

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


    public void updateArticleViewCount(Long id) {
//        LambdaUpdateWrapper<Article> updateWrapper =new LambdaUpdateWrapper<>();
//        updateWrapper.eq(Article::getId, article.getId());
//        updateWrapper.eq(Article::getViewCounts, article.getViewCounts());
//        Article updateArticle =new Article();
//        updateArticle.setViewCounts(article.getViewCounts()+1);
//        articleMapper.update(updateArticle, updateWrapper);
        redisService.saveArticleView(id);

    }


    public void updateArticleCommentCount(Long ArticleId) {
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

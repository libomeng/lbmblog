package com.lbm.controller;

import com.lbm.common.aop.LogAnnotation;
import com.lbm.common.cache.Cache;

import com.lbm.config.RedisKeyConfig;
import com.lbm.dao.dos.Archives;
import com.lbm.dao.entity.Article;
import com.lbm.service.ArticleService;
import com.lbm.service.RedisService;
import com.lbm.vo.ArticleVo;
import com.lbm.vo.HotArticleVo;
import com.lbm.vo.Result;
import com.lbm.vo.params.ArticleParam;
import com.lbm.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("articles")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @Autowired
    RedisService redisService;

    @LogAnnotation
    @GetMapping
    public Result getList(){
       Result result  = articleService.getSimpleList();
       return result;
    }



    /**
     * 文章首页、列表
     * @param pageParams
     * @return
     */
    @LogAnnotation
    @PostMapping
    public Result Articles(@RequestBody PageParams pageParams){
        List<ArticleVo> articleVoList = articleService.findArticles(pageParams);

        return Result.success(articleVoList);
    }

    @PostMapping("/hot")
    public Result hotArticles(){
        int limit =2;
        List<HotArticleVo> articles = articleService.listHotArticles(limit);
        return Result.success(articles);
    }

    @PostMapping("/new")
    public  Result newArticles(){
        int limit =2;
        List<ArticleVo> articleVos =articleService.listNewArticles(limit);
        return Result.success(articleVos);
    }

    @PostMapping("/listArchives")
    public Result getArchivesCreateTimeList(){
        List<Archives> archives = articleService.getArchivesCreateTimeList();
        return Result.success(archives);
    }

    @PostMapping("/view/{id}")
    public Result ArticlesBody(@PathVariable("id")Long id){
        ArticleVo articleVo = articleService.findArticlesById(id);
        return Result.success(articleVo);
    }

    @PostMapping("/publish")
    public Result publishArticle(@RequestBody ArticleParam articleParam){
        Long articleId =articleService.createArticle(articleParam);
        ArticleVo articleVo =new ArticleVo();
        articleVo.setId(articleId);
        redisService.removeMap(RedisKeyConfig.ARTICLE_VO_LIST);
        return Result.success(articleVo);
    }

}

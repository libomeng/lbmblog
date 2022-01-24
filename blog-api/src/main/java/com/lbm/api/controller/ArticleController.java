package com.lbm.api.controller;

import com.lbm.api.common.aop.LogAnnotation;

import com.lbm.api.config.RedisKeyConfig;
import com.lbm.api.dao.dos.Archives;
import com.lbm.api.service.ArticleService;
import com.lbm.api.service.RedisService;
import com.lbm.api.vo.ArticleVo;
import com.lbm.api.vo.HotArticleVo;
import com.lbm.api.vo.Result;
import com.lbm.api.vo.params.ArticleParam;
import com.lbm.api.vo.params.ArticleSimpleParam;
import com.lbm.api.vo.params.PageParams;
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
    @PostMapping("/simple")
    public Result getList(@RequestBody(required = false)ArticleSimpleParam param){
       Result result  = articleService.getSimpleList(param);
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
        Result result = articleService.listNewArticles(limit);
        return result;
    }

    @PostMapping("/listArchives")
    public Result getArchivesCreateTimeList(){
        List<Archives> archives = articleService.getArchivesCreateTimeList();
        return Result.success(archives);
    }

    @PostMapping("/view/{id}")
    public Result ArticlesBody(@PathVariable("id")String id){
        ArticleVo articleVo = articleService.findArticlesById(id);
        return Result.success(articleVo);
    }

    @PostMapping("/publish")
    public Result publishArticle(@RequestBody ArticleParam articleParam){
        String articleId =articleService.createArticle(articleParam);
        ArticleVo articleVo =new ArticleVo();
        articleVo.setId(articleId);
        redisService.removeMap(RedisKeyConfig.ARTICLE_VO_LIST);
        return Result.success(articleVo);
    }

    @GetMapping("count")
    public Result ArticleCount(){
       Integer count = articleService.count();
       return Result.success(count);
    }
}

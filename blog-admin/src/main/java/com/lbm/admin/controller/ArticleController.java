package com.lbm.admin.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbm.admin.entity.Article;
import com.lbm.admin.entity.params.CreateArticleParam;
import com.lbm.admin.entity.vo.ArticleListVo;
import com.lbm.admin.service.ArticleService;
import com.lbm.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lbm
 * @since 2022-01-25
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @GetMapping("/list/{page}/{limit}")
    public Result getList(@PathVariable("page")Integer page, @PathVariable("limit")Integer limit){
        Page<ArticleListVo> articlePage = new Page<>(page,limit);
        Page<ArticleListVo> pageRes = articleService.pageArticleVo(articlePage);
        return Result.success(pageRes);
    }

    @PostMapping("/create")
    public Result create(@RequestBody CreateArticleParam articleParam){
       Result result = articleService.createArticle(articleParam);
       return result;
    }
    @GetMapping("/info/{id}")
    public Result getInfo(@PathVariable("id") String id){
        Result res = articleService.getArticleInfo(id);
        return res;
    }

    @PostMapping("/updateState")
    public Result updateState(@RequestBody Article article){
        Result result = this.articleService.updateState(article);
        return  result;
    }
    @PostMapping("/delete/{id}")
    public Result deleteById(@PathVariable("id") String id){
       Result result = this.articleService.removeArticle(id);
        return result;
    }
    @GetMapping("/getRemoveArticle")
    public Result getRemoveArticle(){
       Result res =  this.articleService.getDeletedArticleList();
       return res;
    }
    @PostMapping("/recycleArticle/{id}")
    public Result recycleArticle(@PathVariable("id") String id){
        Result res = this.articleService.recycleArticleById(id);
        return res;
    }
    @PostMapping("/deleteArticle/{id}/{bodyId}")
    public Result deleteArticle(@PathVariable("id") String id,@PathVariable("bodyId") String bodyId){
       Result res =  this.articleService.deleteArticle(id,bodyId);
       return res;
    }
}


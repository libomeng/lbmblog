package com.lbm.admin.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbm.admin.entity.vo.ArticleListVo;
import com.lbm.admin.service.ArticleService;
import com.lbm.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}


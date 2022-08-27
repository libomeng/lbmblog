package com.lbm.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbm.admin.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lbm.admin.entity.params.CreateArticleParam;
import com.lbm.admin.entity.vo.ArticleListVo;
import com.lbm.common.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lbm
 * @since 2022-01-25
 */
public interface ArticleService extends IService<Article> {

    Page<ArticleListVo> pageArticleVo(Page<ArticleListVo> articlePage);

    Result createArticle(CreateArticleParam articleParam);

    Result getArticleInfo(String id);

    Result updateState(Article article);

    Result recycleArticleById(String id);

    Result getDeletedArticleList();

    Result deleteArticle(String id,String bodyId);

    Result removeArticle(String id);
}

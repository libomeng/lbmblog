package com.lbm.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbm.admin.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lbm.admin.entity.vo.ArticleListVo;

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
}
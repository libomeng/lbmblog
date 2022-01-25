package com.lbm.admin.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbm.admin.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lbm.admin.entity.vo.ArticleListVo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lbm
 * @since 2022-01-25
 */
public interface ArticleMapper extends BaseMapper<Article> {

    Page<ArticleListVo> pageArticleVo(Page<ArticleListVo> articlePage);
}

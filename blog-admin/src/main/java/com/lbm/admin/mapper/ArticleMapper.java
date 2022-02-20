package com.lbm.admin.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbm.admin.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lbm.admin.entity.vo.ArticleListVo;
import com.lbm.admin.entity.vo.ArticleRemoveVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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


    int recycleArticleById(@Param("id") String id);

    List<ArticleRemoveVo> getDeletedArticleList();

    int deleteArticle(@Param("id") String id);
}

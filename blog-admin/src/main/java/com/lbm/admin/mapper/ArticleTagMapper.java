package com.lbm.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lbm.admin.entity.ArticleTag;
import com.lbm.admin.entity.dos.TagCount;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lbm
 * @since 2022-01-27
 */
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

    Integer deleteTagByArticleId(@Param("articleId") String articleId);

    List<TagCount> getTagCount();
}

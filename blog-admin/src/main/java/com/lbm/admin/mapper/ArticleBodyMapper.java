package com.lbm.admin.mapper;

import com.lbm.admin.entity.ArticleBody;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lbm
 * @since 2022-01-25
 */
public interface ArticleBodyMapper extends BaseMapper<ArticleBody> {

    int deleteArticleBody(@Param("bodyId") String bodyId);
}

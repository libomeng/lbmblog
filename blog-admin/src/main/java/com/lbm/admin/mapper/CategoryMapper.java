package com.lbm.admin.mapper;

import com.lbm.admin.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lbm.admin.entity.vo.CategoryVo;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lbm
 * @since 2022-01-25
 */
public interface CategoryMapper extends BaseMapper<Category> {

    List<CategoryVo> getCategoryVos();

    Integer checkCategoryInTheArticle(@Param("id") String id);
}

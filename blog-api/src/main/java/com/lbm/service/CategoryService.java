package com.lbm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lbm.dao.entity.Category;
import com.lbm.vo.CategoryVo;

import java.util.List;

public interface CategoryService extends IService<Category> {
    /**
     * 根据ID查询文章分类
     * @param categoryId
     * @return
     */
    CategoryVo findCategoryById(Long categoryId);

    List<Category> findAllCategorys();

    Category findDetailCategoryById(Long id);
}

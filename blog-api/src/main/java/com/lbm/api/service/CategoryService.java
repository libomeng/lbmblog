package com.lbm.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lbm.api.dao.entity.Category;
import com.lbm.api.vo.CategoryVo;
import com.lbm.api.vo.Result;

import java.util.List;

public interface CategoryService extends IService<Category> {
    /**
     * 根据ID查询文章分类
     * @param categoryId
     * @return
     */
    CategoryVo findCategoryById(String categoryId);

    List<Category> findAllCategorys();

    Category findDetailCategoryById(String id);

    Result getAllArticleCountForCategory();
}

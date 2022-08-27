package com.lbm.admin.service;

import com.lbm.admin.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lbm.common.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lbm
 * @since 2022-01-25
 */
public interface CategoryService extends IService<Category> {

    Result updateCategory(Category category);

    Result addCategory(Category category);

    Result getCategoryVos();

    Result checkAndRemove(String id);
}

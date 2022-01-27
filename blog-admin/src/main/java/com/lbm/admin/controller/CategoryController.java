package com.lbm.admin.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.lbm.admin.entity.Category;
import com.lbm.admin.entity.vo.CategorySelectorVo;
import com.lbm.admin.service.CategoryService;
import com.lbm.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lbm
 * @since 2022-01-25
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping
    public Result getList(){
        List<Category> list = categoryService.list();
        List<CategorySelectorVo> categorySelectorVoList = Category.copyList(list);
        return Result.success(categorySelectorVoList);
    }
}


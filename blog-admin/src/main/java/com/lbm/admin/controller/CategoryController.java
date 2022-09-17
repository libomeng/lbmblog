package com.lbm.admin.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.lbm.admin.entity.Category;
import com.lbm.admin.entity.vo.CategorySelectorVo;
import com.lbm.admin.service.CategoryService;
import com.lbm.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 查询文章分类——列表选择器形式
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAuthority('sys:demo')")
    public Result getCategorySelectorVo(){
        List<Category> list = categoryService.list();
        List<CategorySelectorVo> categorySelectorVoList = Category.copyList(list);
        return Result.success(categorySelectorVoList);
    }
    @GetMapping("/getList")
    @PreAuthorize("hasAuthority('sys:demo')")
    public Result getList(){
       Result result =  categoryService.getCategoryVos();
        return result;
    }
    @PreAuthorize("hasAuthority('sys:admin')")
    @PostMapping("/update")
    public Result update(@RequestBody Category category){
        Result res = categoryService.updateCategory(category);
        return res;
    }
    @PreAuthorize("hasAuthority('sys:admin')")
    @PostMapping("/add")
    public Result add(@RequestBody Category category){
        Result res = categoryService.addCategory(category);
        return res;
    }
    @PreAuthorize("hasAuthority('sys:admin')")
    @PostMapping("/remove/{id}")
    public Result remove(@PathVariable("id")String id){
        Result result = categoryService.checkAndRemove(id);
        return result;
    }
}


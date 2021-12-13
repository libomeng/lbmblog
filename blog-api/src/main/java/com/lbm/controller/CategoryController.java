package com.lbm.controller;

import com.lbm.dao.entity.Category;
import com.lbm.service.CategoryService;
import com.lbm.vo.CategoryVo;
import com.lbm.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categorys")
public class CategoryController {

   @Autowired
   CategoryService categoryService;

    @GetMapping
    public Result findAllCategorys(){

       List<Category> categories= categoryService.findAllCategorys();
        return Result.success(categories);
    }


    @GetMapping("/detail")
    public  Result detail(){
        List<Category> allCategorys = categoryService.findAllCategorys();
        return Result.success(allCategorys);
    }

    @GetMapping("/detail/{id}")
    public  Result detailById(@PathVariable("id") Long id){
        Category category = categoryService.findDetailCategoryById(id);
        return Result.success(category);
    }

}

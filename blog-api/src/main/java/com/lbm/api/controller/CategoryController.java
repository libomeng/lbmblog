package com.lbm.api.controller;

import com.lbm.api.dao.entity.Category;
import com.lbm.api.service.CategoryService;
import com.lbm.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
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
    public  Result detailById(@PathVariable("id") String id){
        Category category = categoryService.findDetailCategoryById(id);
        return Result.success(category);
    }

    @GetMapping("/count")
    public Result getCount(){
        Integer count = categoryService.count();
        return Result.success(count);
    }
    /**
     * 获取该标签下的文章数量
     */
    @GetMapping("allArticleCountForCategory")
    public Result getAllArticleCountForCategory(){
        Result result = categoryService.getAllArticleCountForCategory();
        return result;
    }

}

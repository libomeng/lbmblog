package com.lbm.api.controller;

import com.lbm.api.dao.entity.Tag;
import com.lbm.api.service.TagService;
import com.lbm.api.vo.DetailTagVo;
import com.lbm.api.vo.Result;
import com.lbm.api.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagsController {
   @Autowired
    TagService tagService;

    /**
     * 获取在文章中出现次数最多的标签
     * limit 截取标签个数
     * @return
     */
    @GetMapping("/hot")
    public Result hot(){
        int limit =2;
         List<Tag> hotTags = tagService.hots(limit);
        return Result.success(hotTags);
    }

    @GetMapping("/count")
    public Result getTagCount(){
        Result result = tagService.getTagCount();
        return result;
    }


    @GetMapping
    public Result findAllTags(){
        List<Tag> tags =tagService.findAllTags();
        return Result.success(tags);
    }

    @GetMapping("/detail")
    public Result DetailTags(){
        List<DetailTagVo> detailTagVos = tagService.findDetailTags();
        return Result.success(detailTagVos);
    }
    @GetMapping("/detail/{id}")
    public Result DetailTagsById(@PathVariable("id") String id){
       TagVo detailTagVo = tagService.findTagById(id);
        return Result.success(detailTagVo);
    }

    @GetMapping("/total")
    public Result TagTotal(){
        Integer count = tagService.count();
        return Result.success(count);
    }
}

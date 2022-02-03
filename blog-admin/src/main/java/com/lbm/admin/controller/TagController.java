package com.lbm.admin.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.lbm.admin.entity.Tag;
import com.lbm.admin.entity.params.TagQueryWrapperParam;
import com.lbm.admin.entity.vo.TagSelectorVo;
import com.lbm.admin.service.TagService;
import com.lbm.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/tag")
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping
    public Result getTagList(){
        List<Tag> list = tagService.list();
        List<TagSelectorVo> tagSelectorVos = Tag.copyList(list);
        return Result.success(tagSelectorVos);
    }

    @PostMapping("/getConditionList/{page}/{limit}")
    public Result getConditionList(@PathVariable("page")Integer page, @PathVariable("limit")Integer limit,@RequestBody(required = false) TagQueryWrapperParam param){
       Result result =  this.tagService.getConditionList(page,limit,param);
       return result;
    }

    @PostMapping("/update")
    public Result update(@RequestBody Tag tag){
        boolean res = tagService.updateById(tag);
        if(!res){
            return Result.fail("标签修改失败");
        }
        return Result.success("标签修改成功");
    }

    @PostMapping("/remove/{id}")
    public Result remove(@PathVariable("id")String id){
        boolean res = this.tagService.removeById(id);
        if(!res){
            return Result.fail("删除标签失败");
        }
        return Result.success("删除标签成功");
    }
}


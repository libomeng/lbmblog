package com.lbm.admin.controller;


import com.lbm.admin.entity.Tag;
import com.lbm.admin.entity.vo.TagSelectorVo;
import com.lbm.admin.service.TagService;
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

}


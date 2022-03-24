package com.lbm.admin.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.lbm.admin.service.CommentService;
import com.lbm.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lbm
 * @since 2022-01-25
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/getList/{page}/{limit}")
    public Result getList(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit) {
        Result res = commentService.getList(page, limit);
        return res;
    }
}


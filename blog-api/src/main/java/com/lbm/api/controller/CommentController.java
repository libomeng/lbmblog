package com.lbm.api.controller;

import com.lbm.api.service.CommentService;
import com.lbm.api.vo.CommentVo;
import com.lbm.api.vo.Result;
import com.lbm.api.vo.params.CommentParams;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("article/{id}")
    public Result getComments(@PathVariable("id") Long ArticleId){
        List<CommentVo> commentVos = commentService.commentsByArticleId(ArticleId);
        return Result.success(commentVos);
    }

    @PostMapping("/create/change")
    public Result createComment(@RequestBody CommentParams commentParams){
        if(StringUtil.isNullOrEmpty(commentParams.getContent())){
            return Result.fail(1111, "请输入评论内容");
        }

        commentService.createComment(commentParams);
        return  Result.success(null);
    }
}

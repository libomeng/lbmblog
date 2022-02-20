package com.lbm.api.service;

import com.lbm.api.vo.CommentVo;
import com.lbm.api.vo.params.CommentParams;

import java.util.List;

public interface CommentService {
    List<CommentVo> commentsByArticleId(String ArticleId);

    void createComment(CommentParams commentParams);
}

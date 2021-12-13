package com.lbm.service;

import com.lbm.dao.entity.Comment;
import com.lbm.vo.CommentVo;
import com.lbm.vo.params.CommentParams;

import java.util.List;

public interface CommentService {
    List<CommentVo> commentsByArticleId(Long ArticleId);

    void createComment(CommentParams commentParams);
}

package com.lbm.api.vo.params;

import lombok.Data;

@Data
public class CommentParams {

    private String articleId;

    private String content;

    private String parent;

    private String toUserId;
}

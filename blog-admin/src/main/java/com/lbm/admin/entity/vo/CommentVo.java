package com.lbm.admin.entity.vo;

import lombok.Data;

@Data
public class CommentVo {
    private String id; //用户id
    private String nickName; //用户昵称
    private String ip; //用户IP
    private String lastTime; //评论时间
    private String content; //评论内容
}

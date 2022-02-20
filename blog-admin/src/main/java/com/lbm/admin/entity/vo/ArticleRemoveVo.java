package com.lbm.admin.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleRemoveVo {
    private String id;
    private String bodyId;
    private String title;
    private String categoryName;
    private Date gmtModified;
}

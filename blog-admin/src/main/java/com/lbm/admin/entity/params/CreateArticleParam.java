package com.lbm.admin.entity.params;

import lombok.Data;

import java.util.List;

@Data
public class CreateArticleParam {
    private String title;
    private String summary;
    private String img;
    private String value;
    private String html;
    private String category;
    private List<String> tags;
    private Integer words;
    private Integer readTime;
    private Integer isPublish;
}

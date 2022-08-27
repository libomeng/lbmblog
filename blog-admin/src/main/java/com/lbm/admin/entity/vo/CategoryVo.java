package com.lbm.admin.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id ;
    private String categoryName;
    private  String description;
    private Integer count; //该标签下文章的数量

}

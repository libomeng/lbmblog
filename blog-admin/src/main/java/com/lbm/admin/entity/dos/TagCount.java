package com.lbm.admin.entity.dos;

import lombok.Data;

/**
 * 标签下文章数量的虚拟实体类
 */
@Data
public class TagCount {
    private String id;
    private Integer articleCount;
}

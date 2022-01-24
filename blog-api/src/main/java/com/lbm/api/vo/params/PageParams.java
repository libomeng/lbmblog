package com.lbm.api.vo.params;

import lombok.Data;

@Data
public class PageParams {
    private Long categoryId;

    private String name;

    private Integer page;

    private Integer pageSize;

    private String sort;

    private Long tagId;

    private String year;

    private String month;
}

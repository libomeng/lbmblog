package com.lbm.admin.entity.params;

import lombok.Data;

import java.util.Date;

@Data
public class TagQueryWrapperParam {
    private String tagName;
    private Date begin;
    private Date end;
}

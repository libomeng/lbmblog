package com.lbm.admin.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserinfoVo  {
    private String name;
    private String avatar;
    private List roles;
}

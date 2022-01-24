package com.lbm.admin.entity.param;

import lombok.Data;

/**
 * 接收用户登录信息的实体类
 */
@Data
public class LoginInfo {
    private String username;
    private String password;
}

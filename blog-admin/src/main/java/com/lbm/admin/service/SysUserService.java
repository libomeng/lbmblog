package com.lbm.admin.service;

import com.lbm.admin.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lbm.admin.entity.params.Login;
import com.lbm.common.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lbm
 * @since 2022-01-25
 */
public interface SysUserService extends IService<SysUser> {

    Result login(Login login);

    Result logout();

    List<String> selectUserAuthorities(String id);

    Result getUserInfo();
}

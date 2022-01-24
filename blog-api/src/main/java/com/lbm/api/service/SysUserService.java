package com.lbm.api.service;

import com.lbm.api.dao.entity.SysUser;
import com.lbm.api.vo.Result;
import com.lbm.api.vo.UserVo;


public interface SysUserService {


    /**
     * 根据ID查找一个用户
     *
     * @param authorId
     * @return
     */
    SysUser findOneUser(String authorId);

    /**
     * 验证用户名和密码
     *
     * @param account
     * @param psw
     * @return
     */
    SysUser checkUser(String account, String psw);


    /**
     * 根据token读取用户信息
     *
     * @param token
     * @return
     */
    Result currentUser(String token);

    /**
     * 检查账号是否已经存在
     *
     * @param account
     * @return
     */
    boolean findAccount(String account);

    /**
     * 在数据库中添加用户
     *
     * @param user
     * @return
     */
    Long addUser(SysUser user);

    /**
     * 根据ID查询UserVo对象
     * @param Id  User的ID
     * @return
     */
    UserVo findUserVoById(String Id);
}

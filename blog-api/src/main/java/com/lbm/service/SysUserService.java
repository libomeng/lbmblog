package com.lbm.service;

import com.lbm.dao.entity.SysUser;
import com.lbm.dao.mapper.SysUserMapper;
import com.lbm.vo.Result;
import com.lbm.vo.UserVo;
import com.lbm.vo.params.RegisterParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface SysUserService {


    /**
     * 根据ID查找一个用户
     *
     * @param authorId
     * @return
     */
    SysUser findOneUser(long authorId);

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
     * @param ID  User的ID
     * @return
     */
    UserVo findUserVoById(Long ID);
}

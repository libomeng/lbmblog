package com.lbm.service;

import com.lbm.vo.LoginVo;
import com.lbm.vo.Result;
import com.lbm.vo.params.LoginParams;

public interface LoginService {
    /**
     * 用户登录功能
     * @param loginParams 登录参数对象
     * @return
     */
    Result login(LoginParams loginParams);

    /**
     * 用户登出功能
     * @param token
     * @return
     */
    Result logout(String token);
}

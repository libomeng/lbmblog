package com.lbm.service;

import com.lbm.vo.Result;
import com.lbm.vo.params.RegisterParams;

public interface RegisterService {
    /**
     * 注册账号
     * @param registerParams
     * @return
     */
    Result registerUser(RegisterParams registerParams);
}

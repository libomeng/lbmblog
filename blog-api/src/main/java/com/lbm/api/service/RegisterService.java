package com.lbm.api.service;

import com.lbm.api.vo.Result;
import com.lbm.api.vo.params.RegisterParams;

public interface RegisterService {
    /**
     * 注册账号
     * @param registerParams
     * @return
     */
    Result registerUser(RegisterParams registerParams);
}

package com.lbm.api.service;

import com.lbm.api.dao.entity.GuestUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lbm.api.vo.params.GuestUserParam;
import com.lbm.common.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lbm
 * @since 2022-02-21
 */
public interface GuestUserService extends IService<GuestUser> {

    Result issueToken(GuestUserParam param,String token);

    Result getUserInfo(String token);
}

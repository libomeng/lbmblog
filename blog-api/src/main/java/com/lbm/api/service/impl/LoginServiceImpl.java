package com.lbm.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.lbm.api.dao.entity.SysUser;
import com.lbm.api.service.LoginService;
import com.lbm.api.service.SysUserService;
import com.lbm.api.util.JWTUtils;
import com.lbm.api.vo.ErrorCode;
import com.lbm.api.vo.LoginVo;
import com.lbm.api.vo.Result;
import com.lbm.api.vo.params.LoginParams;
import io.netty.util.internal.StringUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private  RedisTemplate redisTemplate;
    //加密盐
     static final String slat="lbmlbmlbm3343";

    @Override
    public Result login(LoginParams loginParams) {
        /**
         * 1.验证用户名密码(是否为空) 查询结果是否为空
         * 2.验证通过后创建Token
         * 3.存储Token到Redis
         * 4.返回Token
         */
        String account = loginParams.getAccount();
        String password = loginParams.getPassword();
        if (StringUtil.isNullOrEmpty(account) && StringUtil.isNullOrEmpty(password)) {
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        //MD5加密
        String psw = DigestUtils.md5Hex(password+slat);
        SysUser sysUser = sysUserService.checkUser(account, psw);
        if (sysUser == null) {
            return Result.fail(ErrorCode.ACCOUNT_PWD_ERROR.getCode(), ErrorCode.ACCOUNT_PWD_ERROR.getMsg());
        }
        //生成ToKen
        String token = JWTUtils.createToken(sysUser.getId());
        LoginVo loginVo = userToLoginVo(sysUser);

        //存储Token到Redis
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(loginVo)
                                        ,15, TimeUnit.MINUTES);


        return Result.success(token);
    }

    @Override
    public Result logout(String token) {
        /**
         * 从redis中删除token
         */
        redisTemplate.delete("TOKEN_"+token);
        return Result.success(null);
    }

    /**
     * user转换为loginVo
     * @param sysUser
     * @return
     */
    private LoginVo userToLoginVo(SysUser sysUser) {
        LoginVo loginVo =new LoginVo();
        BeanUtils.copyProperties(sysUser, loginVo);
        return loginVo;
    }
}

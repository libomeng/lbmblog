package com.lbm.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.lbm.api.dao.entity.SysUser;
import com.lbm.api.service.RegisterService;
import com.lbm.api.service.SysUserService;
import com.lbm.api.util.JWTUtils;
import com.lbm.api.util.MD5Utils;
import com.lbm.api.vo.ErrorCode;
import com.lbm.api.vo.LoginVo;
import com.lbm.api.vo.Result;
import com.lbm.api.vo.params.RegisterParams;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.concurrent.TimeUnit;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    SysUserService sysUserService;

    @Autowired
    RedisTemplate redisTemplate;

    @Transactional
    @Override
    public Result registerUser(@RequestBody RegisterParams registerParams) {
//        String account = registerParams.getAccount();
//        String password = registerParams.getPassword();
//        String nickname = registerParams.getNickname();
//        if (StringUtil.isNullOrEmpty(account)
//                || StringUtil.isNullOrEmpty(password)
//                || StringUtil.isNullOrEmpty(nickname)) {
//            return Result.fail(ErrorCode.REGISTER_NO_ALL.getCode(), ErrorCode.REGISTER_NO_ALL.getMsg());
//        }
//
//        //验证用户名是否存在 true为存在
//        boolean existUser =sysUserService.findAccount(account);
//        if(existUser){
//            return Result.fail(ErrorCode.USER_EXIST.getCode(), ErrorCode.USER_EXIST.getMsg());
//        }
//        //新建用户
//        SysUser user =new SysUser();
//        user.setAccount(account);
//        user.setPassword(MD5Utils.passwordToMD5(password));
//        user.setNickname(nickname);
//        user.setAdmin(true);
//        user.setAvatar(nickname);
//        user.setEmail("");
//        user.setCreateDate(System.currentTimeMillis());
//        user.setLastLogin(System.currentTimeMillis());
//        user.setAvatar("/static/user/user_1.png");
//        String id = sysUserService.addUser(user);
//        String token = JWTUtils.createToken(id);
////        RedisUtil.saveToKen(token);
//        LoginVo loginVo =new LoginVo();
//        BeanUtils.copyProperties(user, loginVo);
//        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(loginVo),24, TimeUnit.MINUTES);
//        return Result.success(token);
        return null;
    }
}

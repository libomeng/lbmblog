package com.lbm.service.impl;

import com.alibaba.fastjson.JSON;
import com.lbm.dao.entity.SysUser;
import com.lbm.service.RegisterService;
import com.lbm.service.SysUserService;
import com.lbm.util.JWTUtils;
import com.lbm.util.MD5Utils;
import com.lbm.util.RedisUtil;
import com.lbm.vo.ErrorCode;
import com.lbm.vo.LoginVo;
import com.lbm.vo.Result;
import com.lbm.vo.params.RegisterParams;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.xml.ws.Action;
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
        String account = registerParams.getAccount();
        String password = registerParams.getPassword();
        String nickname = registerParams.getNickname();
        if (StringUtil.isNullOrEmpty(account)
                || StringUtil.isNullOrEmpty(password)
                || StringUtil.isNullOrEmpty(nickname)) {
            return Result.fail(ErrorCode.REGISTER_NO_ALL.getCode(), ErrorCode.REGISTER_NO_ALL.getMsg());
        }

        //验证用户名是否存在 true为存在
        boolean existUser =sysUserService.findAccount(account);
        if(existUser){
            return Result.fail(ErrorCode.USER_EXIST.getCode(), ErrorCode.USER_EXIST.getMsg());
        }
        //新建用户
        SysUser user =new SysUser();
        user.setAccount(account);
        user.setPassword(MD5Utils.passwordToMD5(password));
        user.setNickname(nickname);
        user.setAdmin(0);
        user.setAvatar(nickname);
        user.setEmail("");
        user.setDeleted(0);
        user.setCreateDate(System.currentTimeMillis());
        user.setLastLogin(System.currentTimeMillis());
        user.setAvatar("/static/user/user_1.png");
        Long id = sysUserService.addUser(user);
        String token = JWTUtils.createToken(id);
//        RedisUtil.saveToKen(token);
        LoginVo loginVo =new LoginVo();
        BeanUtils.copyProperties(user, loginVo);
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(loginVo),24, TimeUnit.MINUTES);
        return Result.success(token);
    }
}

package com.lbm.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lbm.api.dao.entity.GuestUser;
import com.lbm.api.dao.entity.SysUser;
import com.lbm.api.dao.mapper.SysUserMapper;
import com.lbm.api.service.GuestUserService;
import com.lbm.api.service.SysUserService;
import com.lbm.api.util.JWTUtils;
import com.lbm.api.vo.ErrorCode;
import com.lbm.api.vo.LoginVo;
import com.lbm.api.vo.Result;
import com.lbm.api.vo.UserVo;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private GuestUserService guestUserService;

    @Autowired
    RedisTemplate redisTemplate;


    @Override
    public SysUser findOneUser(String UserId) {
        SysUser sysUser = sysUserMapper.selectById(UserId);
        return sysUser;
    }

    @Override
    public SysUser checkUser(String account, String psw) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(SysUser::getAccount, account);
        queryWrapper.eq(SysUser::getPassword, psw);
        queryWrapper.select(SysUser::getId, SysUser::getAccount, SysUser::getNickname, SysUser::getAvatar);
        queryWrapper.last("Limit 1");
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        return sysUser;
    }

    @Override
    public Result currentUser(String token) {
        //校验token是否为空
        if (StringUtil.isNullOrEmpty(token)) {
            return Result.fail(ErrorCode.TOKEN_ERROR.getCode(), ErrorCode.TOKEN_ERROR.getMsg());
        }
        String userId = JWTUtils.checkToken(token);
        //验证token中的body是否为空
        if (StringUtils.isEmpty(userId)) {
            return Result.fail(ErrorCode.TOKEN_ERROR.getCode(), ErrorCode.TOKEN_ERROR.getMsg());
        }
        GuestUser user = this.guestUserService.getById(userId);
        if(user==null){
            return Result.fail("数据库中无此用户");
        }
        LoginVo loginVo = new LoginVo(user);
        return Result.success("获取用户信息成功",loginVo);
    }

    @Override
    public boolean findAccount(String account) {
        LambdaQueryWrapper<SysUser> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.select(SysUser::getAccount);
        queryWrapper.eq(SysUser::getAccount, account);
        queryWrapper.last("LIMIT 1");
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        if(sysUser != null){
            return true;
        }
        return false;
    }

    @Override
    public String addUser(SysUser user) {
         sysUserMapper.insert(user);
         LambdaQueryWrapper<SysUser> queryWrapper =new LambdaQueryWrapper<>();
         queryWrapper.eq(SysUser::getAccount, user.getAccount());
         queryWrapper.select(SysUser::getId);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        String id = sysUser.getId();
        return id;
    }

    @Override
    public UserVo findUserVoById(String ID) {
        LambdaQueryWrapper<SysUser> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.select(SysUser::getId,SysUser::getAvatar,SysUser::getNickname);
        queryWrapper.eq(SysUser::getId, ID);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        UserVo userVo =new UserVo();
        BeanUtils.copyProperties(sysUser, userVo);
        return userVo;
    }


}

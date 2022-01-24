package com.lbm.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lbm.api.dao.entity.SysUser;
import com.lbm.api.dao.mapper.SysUserMapper;
import com.lbm.api.service.SysUserService;
import com.lbm.api.util.JWTUtils;
import com.lbm.api.vo.ErrorCode;
import com.lbm.api.vo.LoginVo;
import com.lbm.api.vo.Result;
import com.lbm.api.vo.UserVo;
import io.netty.util.internal.StringUtil;
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
        Map<String, Object> body = JWTUtils.checkToken(token);

        //验证token中的body是否为空
        if (body == null) {
            return Result.fail(ErrorCode.TOKEN_ERROR.getCode(), ErrorCode.TOKEN_ERROR.getMsg());
        }
        String userJson = (String) redisTemplate.opsForValue().get("TOKEN_" + token);

        //验证redis中是否是否存在token
        if (StringUtil.isNullOrEmpty(userJson)) {
            return Result.fail(ErrorCode.TOKEN_ERROR.getCode(), ErrorCode.TOKEN_ERROR.getMsg());
        }
        LoginVo loginVo = JSON.parseObject(userJson, LoginVo.class);
        String idFromJWT = (String) body.get("userID");

        //由于JJWT有BUG 包装进MAP的LONG类型取出时为Integer,所以使用强转
        String idFromRedis = loginVo.getId().toString();

        //验证前端返回token是否与redis中一致
        if (!idFromJWT.equals(idFromRedis)) {
            return Result.fail(ErrorCode.TOKEN_ERROR.getCode(), ErrorCode.TOKEN_ERROR.getMsg());
        }
        return Result.success(loginVo);
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
    public Long addUser(SysUser user) {
         sysUserMapper.insert(user);
         LambdaQueryWrapper<SysUser> queryWrapper =new LambdaQueryWrapper<>();
         queryWrapper.eq(SysUser::getAccount, user.getAccount());
         queryWrapper.select(SysUser::getId);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        Long id = sysUser.getId();
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

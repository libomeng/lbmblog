package com.lbm.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lbm.api.dao.entity.GuestUser;
import com.lbm.api.dao.mapper.GuestUserMapper;
import com.lbm.api.service.GuestUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbm.api.util.JWTUtils;
import com.lbm.api.vo.params.GuestUserParam;
import com.lbm.common.Result;
import com.lbm.common.uitl.ObjectUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lbm
 * @since 2022-02-21
 */
@Service
public class GuestUserServiceImpl extends ServiceImpl<GuestUserMapper, GuestUser> implements GuestUserService {
    @Autowired
    GuestUserMapper guestUserMapper;

    /**
     * 验证token有效性
     *
     * @param param
     * @param token
     * @return
     */
    @Override
    public Result createToken(GuestUserParam param, String token) {
        if (ObjectUtil.checkObjFieldIsNull(param)) {
            return Result.fail("IP地址获取错误");
        }
        if (StringUtils.isEmpty(token) || "null".equals(token) || "undefined".equals(token)) {  //如果token为空，创建token,并将用户数据插入表
            String createdToken = this.createUserAndToken(param);
            if (StringUtils.isEmpty(createdToken)) {
                return Result.fail("数据库创建数据错误");
            }
            return Result.success("生成token成功", createdToken);
        }
        String userId = JWTUtils.checkToken(token);     //token不为空，提取token中的用户id
        GuestUser user = this.getById(userId);
        if (user == null) {     //如果查询数据库中无此用户，则插入此用户信息
            String createdToken = this.createUserAndToken(param);
            if (StringUtils.isEmpty(createdToken)) {
                return Result.fail("数据库创建数据错误");
            }
            return Result.success("生成token成功", createdToken);
        }
        //查询到用户，更新用户最后登录时间
        user.setLastTime(new Date());
        this.updateById(user);
        return Result.success("token验证成功", token);
    }

    private String createUserAndToken(GuestUserParam param) {
        GuestUser guestUser = new GuestUser();
        guestUser.setIp(param.getIp());
        guestUser.setCityName(param.getCityName());
        guestUser.setLastTime(new Date());
        boolean save = this.save(guestUser);
        if (!save) {
            return null;
        }
        String token = JWTUtils.createToken(guestUser.getId());
        return token;
    }
}

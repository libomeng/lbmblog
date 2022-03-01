package com.lbm.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.lbm.api.dao.entity.GuestUser;
import com.lbm.api.dao.mapper.GuestUserMapper;
import com.lbm.api.service.GuestUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbm.api.util.JWTUtils;
import com.lbm.api.util.UserThreadLocal;
import com.lbm.api.vo.ErrorCode;
import com.lbm.api.vo.LoginVo;
import com.lbm.api.vo.params.GuestUserParam;
import com.lbm.common.Result;
import com.lbm.common.uitl.ObjectUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
     * 签发token
     *
     * @param param
     * @param token
     * @return
     */

    @Override
    public Result issueToken(GuestUserParam param, String token) {
        /**
         * token基础验证
         */
        if (param == null) {
            return Result.fail("token请求参数错误");
        }
        if (StringUtils.isEmpty(token) || "null".equals(token) || "undefined".equals(token)) {  //如果token为空，创建token,并将用户数据插入表
            String createdToken = this.createUserAndToken(param);
            if (StringUtils.isEmpty(createdToken)) {
                return Result.fail("数据库创建数据错误");
            }
            return Result.success("生成token成功", createdToken);
        }
        String userId = "";
        /**
         * 解析token
         */
        try {
            userId = JWTUtils.checkToken(token);
        } catch (Exception e) {
            /**
             * token解析错误，签发新的token
             */
            String createdToken = this.createUserAndToken(param);
            if (StringUtils.isEmpty(createdToken)) {
                return Result.fail("数据库创建数据错误");
            }
        }
        //token不为空，提取token中的用户id
        GuestUser user = this.getById(userId);
        if (user == null) {     //如果查询数据库中无此用户，则插入此用户信息
            String createdToken = this.createUserAndToken(param);
            if (StringUtils.isEmpty(createdToken)) {
                return Result.fail("数据库创建数据错误");
            }
            return Result.success("生成token成功", createdToken);
        }
        /**
         * 更新用户信息
         */
        if(StringUtils.isNotEmpty(param.getIp())){
            user.setIp(param.getIp());
        }
        if(StringUtils.isNotEmpty(param.getEmail())){
            user.setEmail(param.getEmail());
        }
        if(StringUtils.isNotEmpty(param.getNickName())){
            user.setNickName(param.getNickName());
        }
        if(StringUtils.isNotEmpty(param.getCityName())){
            user.setCityName(param.getCityName());
        }
        user.setLastTime(new Date());
        this.updateById(user);
        UserThreadLocal.put(user);//将用户加入线程池
        return Result.success("token验证成功", token);
    }

    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    @Override
    public Result getUserInfo(String token) {
        //校验token是否为空
        if (StringUtils.isEmpty(token)) {
            return Result.fail(ErrorCode.TOKEN_ERROR.getCode(), ErrorCode.TOKEN_ERROR.getMsg());
        }
        String userId = JWTUtils.checkToken(token);
        //验证token中的body是否为空
        if (StringUtils.isEmpty(userId)) {
            return Result.fail(ErrorCode.TOKEN_ERROR.getCode(), ErrorCode.TOKEN_ERROR.getMsg());
        }
        GuestUser user = this.getById(userId);
        if (user == null) {
            return Result.fail("数据库中无此用户");
        }
        GuestUserParam userParam = new GuestUserParam();
        BeanUtils.copyProperties(user, userParam);
        return Result.success("获取用户信息成功", userParam);
    }
    /**
     * 签发token并将用户信息入库
     * @param param 用户信息参数
     * @return token
     */
    private String createUserAndToken(GuestUserParam param) {
        GuestUser guestUser = new GuestUser();
        if (StringUtils.isNotEmpty(param.getIp())) {
            guestUser.setIp(param.getIp());
        }
        if (StringUtils.isNotEmpty(param.getCityName())) {
            guestUser.setCityName(param.getCityName());
        }
        if (StringUtils.isNotEmpty(param.getEmail())) {
            guestUser.setEmail(param.getEmail());
        }
        if (StringUtils.isNotEmpty(param.getNickName())) {
            guestUser.setNickName(param.getNickName());
        }
        guestUser.setLastTime(new Date());
        boolean save = this.save(guestUser);
        if (!save) {
            return null;
        }
        String token = JWTUtils.createToken(guestUser.getId());
        return token;
    }
}

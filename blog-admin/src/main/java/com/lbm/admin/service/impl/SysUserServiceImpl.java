package com.lbm.admin.service.impl;

import com.lbm.admin.config.RedisConfig;
import com.lbm.admin.entity.SysUser;
import com.lbm.admin.entity.dos.SysUserDetails;
import com.lbm.admin.entity.params.Login;
import com.lbm.admin.entity.vo.UserinfoVo;
import com.lbm.admin.mapper.SysUserMapper;
import com.lbm.admin.service.RedisService;
import com.lbm.admin.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbm.common.Result;
import com.lbm.common.config.RedisKeyConfig;
import com.lbm.common.uitl.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lbm
 * @since 2022-01-25
 */

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RedisService redisService;
    @Override
    public Result login(Login login) {
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(login.getUsername(),login.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authentication)){
            throw new RuntimeException("用户认证失败");
        }
        SysUserDetails sysUserDetails = (SysUserDetails)authentication.getPrincipal();
        String userid = sysUserDetails.getSysUser().getId();
        //认证成功后生成token
        String token = JWTUtils.createToken(userid);
        redisService.saveSysUserDetails(RedisKeyConfig.USER_DETAILS+userid,sysUserDetails);
        //将用户信息存入Redis
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        return Result.success("登录成功",map);
    }

    @Override
    public Result logout() {
        //SecurityContextHolder中取出用户信息
        SysUserDetails sysUserDetails = (SysUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //从redis中删除
        String key = RedisKeyConfig.USER_DETAILS+sysUserDetails.getSysUser().getId();
        redisService.removeByHash(key);
        return Result.success("用户删除成功");
    }

    @Override
    public List<String> selectUserAuthorities(String id) {
        return  sysUserMapper.selectUserAuthorities(id);
    }

    @Override
    public Result getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUserDetails sysUserDetails = (SysUserDetails)authentication.getPrincipal();
        String userId = sysUserDetails.getSysUser().getId();
       UserinfoVo userInfo = sysUserMapper.getUserInfo(userId);
      List<String> roles =  sysUserMapper.getUserRoles(userId);
      userInfo.setRoles(roles);
        return Result.success(userInfo);
    }
}

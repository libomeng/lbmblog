package com.lbm.admin.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lbm.admin.entity.SysUser;
import com.lbm.admin.entity.dos.SysUserDetails;
import com.lbm.admin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    SysUserService sysUserService;

    /**
     * SpringSecurity用户信息校验
     * @param username
     * @return
     * @throws UsernameNotFoundException
     * @Ar LBM
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<SysUser> sysUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sysUserLambdaQueryWrapper.eq(SysUser::getAccount,username);
        SysUser loginUser = sysUserService.getOne(sysUserLambdaQueryWrapper);
        if(Objects.isNull(loginUser)){
            throw new RuntimeException("用户名或密码错误");
        }
        // 获取用户权限集合
        List<String> authorities = sysUserService.selectUserAuthorities(loginUser.getId());
        SysUserDetails sysUserDetails = new SysUserDetails(loginUser, authorities);
        return sysUserDetails ;
    }
}

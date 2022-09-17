package com.lbm.admin.mapper;

import com.lbm.admin.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lbm.admin.entity.vo.UserinfoVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lbm
 * @since 2022-01-25
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<String> selectUserAuthorities(String id);

    UserinfoVo getUserInfo(String userId);

    List<String> getUserRoles(String userId);
}

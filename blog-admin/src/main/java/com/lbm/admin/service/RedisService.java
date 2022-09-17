package com.lbm.admin.service;

import com.lbm.admin.entity.dos.SysUserDetails;

import java.util.Objects;

public interface RedisService {
     void removeByHash(Object key);

     SysUserDetails getSysUserDetails(Object key);

     void saveSysUserDetails(Object key, SysUserDetails sysUserDetails);
}


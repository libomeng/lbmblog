package com.lbm.admin;

import com.lbm.admin.entity.SysUser;
import com.lbm.admin.mapper.SysUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@SpringBootTest
public class MyTest {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Test
    public void BCryptPasswordEncoderTest(){
        String encode = passwordEncoder.encode("lbm304304");
        System.out.println(encode);
    }
    @Autowired
    SysUserMapper sysUserMapper;
    @Test
    public void sysUserMapperTest(){
        SysUser sysUser = sysUserMapper.selectById("1");
        System.out.println(sysUser);
    }
}

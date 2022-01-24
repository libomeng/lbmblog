package com.lbm.api.controller;

import com.lbm.api.service.SysUserService;
import com.lbm.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/currentUser")
    public Result currentUser(@RequestHeader("Authorization")String token){
        return  sysUserService.currentUser(token);

    }

}

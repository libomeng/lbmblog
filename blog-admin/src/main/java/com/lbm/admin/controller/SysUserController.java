package com.lbm.admin.controller;

import com.lbm.admin.entity.params.Login;
import com.lbm.common.Result;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lbm
 * @since 2022-01-25
 */
@RestController
@RequestMapping("/user")
public class SysUserController {
    @PostMapping("/login")
    public Result login(@RequestBody Login login){
        if(login.getUsername().equals("admin")){
            Map map  =new HashMap();
            map.put("token","admin");
            return Result.success(map);
        }
        return Result.fail("登录失败");
    }
    @GetMapping("/info")
    public Result getUserInfo(){
        Map map = new HashMap();
        map.put("roles","[admin]");
        map.put("name","admin");
        map.put("avatar","https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2F2021%2Fedpic%2F0b%2F17%2F04%2F0b1704a9741f4e7ddd07939877dd3590_1.jpg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1643698253&t=b14e1c15a8294688e206c0c71a0303de");
        return Result.success(map);
    }
}


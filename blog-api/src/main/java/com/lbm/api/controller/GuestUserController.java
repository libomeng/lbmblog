package com.lbm.api.controller;


import com.lbm.api.service.GuestUserService;
import com.lbm.api.vo.params.GuestUserParam;
import com.lbm.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lbm
 * @since 2022-02-21
 */
@RestController
@RequestMapping("/guestUser")
public class GuestUserController {
    @Autowired
    GuestUserService guestUserService;

    @PostMapping("/issue/token")
    public Result issueToken(@RequestBody GuestUserParam param, @RequestHeader(value = "Token", required = false) String token) {
        Result result = this.guestUserService.issueToken(param, token);
        return result;
    }

    @PostMapping("/info")
    public Result getUserInfo(@RequestHeader("Token") String token) {
        Result result = guestUserService.getUserInfo(token);
        return result;
    }
}


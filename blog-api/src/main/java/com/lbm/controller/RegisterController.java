package com.lbm.controller;

import com.lbm.service.RegisterService;
import com.lbm.vo.Result;
import com.lbm.vo.params.RegisterParams;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @PostMapping
    public Result register(@RequestBody RegisterParams registerParams){
        Result result = registerService.registerUser(registerParams);
        return result;

    }

}

package com.lbm.controller;

import com.lbm.service.RedisService;
import com.lbm.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/count")
@RestController
public class CountController {

    @Autowired
    CountService countService;

    @PostMapping
    public Result addCount() {
        Integer count = countService.addCount();
        return Result.success(count);
    }
}

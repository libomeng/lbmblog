package com.lbm.api.controller;

import com.lbm.api.service.UploadService;
import com.lbm.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    UploadService uploadService;

    @PostMapping
    public Result upload(@RequestParam("image") MultipartFile file){

        return uploadService.uploadImage(file);

    }
}

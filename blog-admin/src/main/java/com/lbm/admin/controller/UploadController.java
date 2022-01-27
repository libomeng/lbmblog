package com.lbm.admin.controller;

import com.lbm.admin.service.UploadService;
import com.lbm.common.Result;
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
    @PostMapping("/articleImg")
    public Result uploadArticleImg(@RequestParam("articleImg") MultipartFile file){
           Result res  = uploadService.uploadImg(file);
           return res;
    }
}

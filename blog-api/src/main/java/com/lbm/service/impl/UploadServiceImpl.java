package com.lbm.service.impl;

import com.lbm.service.UploadService;
import com.lbm.util.QiniuUtil;
import com.lbm.vo.Result;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    QiniuUtil qiniuUtil;

    @Value("${qiniu.url}")
    private String url;

    @Override
    public Result uploadImage(MultipartFile file){
        //修改文件名为唯一名字
        String originalFilename = file.getOriginalFilename();
        String filename= UUID.randomUUID().toString()+"."+ StringUtils.substringAfter(originalFilename, ".");
        try {
            boolean result = qiniuUtil.upload(file, filename);
            if(result){
                return Result.success(url+filename);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.fail(999, "文件上传失败");
    }
}

package com.lbm.admin.service.impl;

import com.lbm.admin.entity.Img;
import com.lbm.admin.service.ImgService;
import com.lbm.admin.service.UploadService;
import com.lbm.common.Result;
import com.lbm.common.uitl.QiniuUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    ImgService imgService;
    QiniuUtil qiniuUtil = new QiniuUtil();

    @Value("${qiniu.url}")
    private String url;

    @Override
    @Transactional
    public Result uploadImg(MultipartFile file) {
        //检查数据库中是否有此文件
        String md5 ="";
        try {
           md5 = DigestUtils.md5Hex(file.getBytes());
            Img img = imgService.getById(md5);
            if (img != null) {
                return Result.success("添加成功",img.getUrl());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //修改文件名为唯一名字
        String originalFilename = file.getOriginalFilename();
        String filename = UUID.randomUUID().toString() + "." + StringUtils.substringAfter(originalFilename, ".");
        try {
            boolean result = qiniuUtil.upload(file, filename);
            if (result) {
                String fileUrl = url + filename;
                Img img = new Img();
                img.setId(md5);
                img.setUrl(fileUrl);
                imgService.save(img);
                return Result.success("上传成功",  fileUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.fail(999, "文件上传失败");
    }
}

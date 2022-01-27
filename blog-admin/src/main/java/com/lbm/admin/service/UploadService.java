package com.lbm.admin.service;

import com.lbm.common.Result;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    Result uploadImg(MultipartFile file);
}

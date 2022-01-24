package com.lbm.api.service;

import com.lbm.api.vo.Result;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    Result uploadImage(MultipartFile file);
}

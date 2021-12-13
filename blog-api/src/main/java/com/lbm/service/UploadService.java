package com.lbm.service;

import com.lbm.vo.Result;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    Result uploadImage(MultipartFile file);
}

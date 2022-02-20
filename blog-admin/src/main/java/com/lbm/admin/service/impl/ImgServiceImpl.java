package com.lbm.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbm.admin.entity.Img;
import com.lbm.admin.mapper.ImgMapper;
import com.lbm.admin.service.ImgService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lbm
 * @since 2022-02-21
 */
@Service
public class ImgServiceImpl extends ServiceImpl<ImgMapper, Img> implements ImgService {
}

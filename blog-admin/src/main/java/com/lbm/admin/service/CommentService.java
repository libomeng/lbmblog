package com.lbm.admin.service;

import com.lbm.admin.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lbm.common.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lbm
 * @since 2022-01-25
 */
public interface CommentService extends IService<Comment> {

    Result getList(Integer page, Integer limit);
}

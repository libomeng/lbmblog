package com.lbm.admin.service;

import com.lbm.admin.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lbm.admin.entity.params.TagQueryWrapperParam;
import com.lbm.common.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lbm
 * @since 2022-01-25
 */
public interface TagService extends IService<Tag> {

    Result getConditionList(Integer page, Integer limit, TagQueryWrapperParam param);
}

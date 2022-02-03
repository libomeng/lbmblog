package com.lbm.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbm.admin.entity.Tag;
import com.lbm.admin.entity.params.TagQueryWrapperParam;
import com.lbm.admin.mapper.TagMapper;
import com.lbm.admin.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbm.common.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lbm
 * @since 2022-01-25
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public Result getConditionList(Integer page, Integer limit, TagQueryWrapperParam param) {
        LambdaQueryWrapper<Tag> tagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(param != null){
            if(StringUtils.isNotEmpty(param.getTagName())){
                tagLambdaQueryWrapper.like(Tag::getTagName,param.getTagName());
            }
            if (param.getBegin() !=null) {
                tagLambdaQueryWrapper.ge(Tag::getGmtModified, param.getBegin());
            }
            if (param.getEnd() != null) {
                tagLambdaQueryWrapper.le(Tag::getGmtModified, param.getEnd());
            }
        }
        Page<Tag> tagPage = new Page<>(page,limit);
        Page<Tag> pageRes = this.page(tagPage, tagLambdaQueryWrapper);
        return Result.success("标签获取成功",pageRes);
    }
}

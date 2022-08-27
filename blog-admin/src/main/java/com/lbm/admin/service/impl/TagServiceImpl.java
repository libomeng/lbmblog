package com.lbm.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbm.admin.entity.ArticleTag;
import com.lbm.admin.entity.Tag;
import com.lbm.admin.entity.dos.TagCount;
import com.lbm.admin.entity.params.TagQueryWrapperParam;
import com.lbm.admin.entity.vo.TagVo;
import com.lbm.admin.mapper.TagMapper;
import com.lbm.admin.service.ArticleTagService;
import com.lbm.admin.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbm.common.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    ArticleTagService articleTagService;

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
        List<TagCount>  tagCountList= articleTagService.getTagArticleCount();
        Page<Tag> pageRes = this.page(tagPage, tagLambdaQueryWrapper);
        /**
         * 为TagList 添加一个 ArticleCount属性
         */
        List<TagVo> tagVos = new ArrayList<>();
        for(Tag tag:pageRes.getRecords()){
            TagVo tagVo = Tag.toVo(tag);
            for(TagCount tagCount :tagCountList){
               if(tagVo.getId().equals(tagCount.getId())){
                   tagVo.setArticleCount(tagCount.getArticleCount());
                   tagVos.add(tagVo);
               }
            }
        }
        Page<TagVo> tagVoPage = new Page<>();
        tagVoPage.setCurrent(tagPage.getCurrent());
        tagVoPage.setTotal(tagPage.getTotal());
        tagVoPage.setRecords(tagVos);
        return Result.success("标签获取成功",tagVoPage);
    }

    @Override
    public Boolean deleteTagByArticleID(String articleId) {
        Integer res = articleTagService.deleteTagByArticleId(articleId);
        if(res == 1){
            return true;
        }
        return false;
    }
}

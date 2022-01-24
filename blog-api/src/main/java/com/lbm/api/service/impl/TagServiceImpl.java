package com.lbm.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbm.api.common.cache.Cache;
import com.lbm.api.config.RedisKeyConfig;
import com.lbm.api.dao.dos.TagCount;
import com.lbm.api.dao.entity.Tag;
import com.lbm.api.dao.mapper.TagMapper;
import com.lbm.api.service.RedisService;
import com.lbm.api.service.TagService;
import com.lbm.api.vo.DetailTagVo;
import com.lbm.api.vo.Result;
import com.lbm.api.vo.TagVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Autowired
    TagMapper tagMapper;

    @Autowired
    RedisService redisService;

    @Override
    public List<TagVo> selectByTagId(String ArticleVoId) {
        List<Tag> tags = tagMapper.selectByTagId(ArticleVoId);
        List<TagVo> tagVos = copyList(tags);
        return tagVos;
    }

    private List<TagVo> copyList(List<Tag> tags) {
        List<TagVo> tagVos =new ArrayList<>();
        for (Tag tag : tags) {
               tagVos.add(copy(tag));
        }
        return tagVos;
    }

    private TagVo copy(Tag tag) {
        TagVo tagVo =new TagVo();
        BeanUtils.copyProperties(tag,tagVo);
        return tagVo;
    }

    @Override
    public List<Tag> hots(int limit) {
        String key = RedisKeyConfig.TAG_LIST+"hots";
        List entity = redisService.getEntity(key, List.class);
        if(entity != null){
            return entity;
        }
        List<Tag> hotTags=tagMapper.selectHots(limit);
        redisService.setEntity(key,hotTags);
        return hotTags;
    }
    @Cache
    @Override
    public List<Tag> findAllTags() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
//         queryWrapper.select(Tag::getId,Tag::getTagName);
        List<Tag> tags = tagMapper.selectList(queryWrapper);

        return tags;
    }
    @Cache
    @Override
    public List<DetailTagVo> findDetailTags() {
        List<Tag> Tags = this.findAllTags();
        List<DetailTagVo> detailTagVos =new ArrayList<>();
        for (Tag tag : Tags) {
            DetailTagVo detailTagVo = copyToDetailTagVo(tag);
            detailTagVos.add(detailTagVo);
        }
        return detailTagVos;
    }

    @Override
    public TagVo findTagById(String id) {
        //从redis中获取数据
        String key = RedisKeyConfig.FIND_TAG_BY_ID;
        TagVo entity = redisService.getEntity(key, id, TagVo.class);
        if(entity != null){
            return entity;
        }
        //没有数据才从数据库中获取
        Tag tag = tagMapper.selectById(id);
        TagVo tagVo = copy(tag);
        redisService.setEntity(key,id,tagVo);
        return tagVo;
    }

    @Override
    public Result getTagCount() {
       List<TagCount> tagCountList =  tagMapper.selectTagCount();
        return Result.success("获取热门标签成功",tagCountList);
    }

    private  DetailTagVo copyToDetailTagVo(Tag tag){
        DetailTagVo detailTagVo =new DetailTagVo();
        BeanUtils.copyProperties(tag,detailTagVo);
        return detailTagVo;
    }

}

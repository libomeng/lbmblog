package com.lbm.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.lbm.common.cache.Cache;
import com.lbm.config.RedisKeyConfig;
import com.lbm.dao.dos.TagCount;
import com.lbm.dao.entity.Article;
import com.lbm.dao.entity.Tag;
import com.lbm.dao.mapper.ArticleMapper;
import com.lbm.dao.mapper.TagMapper;
import com.lbm.service.RedisService;
import com.lbm.service.TagService;
import com.lbm.vo.DetailTagVo;
import com.lbm.vo.Result;
import com.lbm.vo.TagVo;
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

    @Cache
    @Override
    public List<TagVo> selectByTagId(Long ArticleVoId) {
        List<Tag> tags = tagMapper.selectByTagId(ArticleVoId);
        List<TagVo> tagVos=copyList(tags);
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
    public TagVo findTagById(Long id) {
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

package com.lbm.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lbm.dao.dos.TagCount;
import com.lbm.dao.entity.Tag;

import java.util.List;

public interface TagMapper extends BaseMapper<Tag>{
    List<Tag> selectByTagId(Long ArticleId);

    List<Tag> selectHots(int limit);

    List<TagCount> selectTagCount();
}

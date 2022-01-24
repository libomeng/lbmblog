package com.lbm.api.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lbm.api.dao.dos.TagCount;
import com.lbm.api.dao.entity.Tag;

import java.util.List;

public interface TagMapper extends BaseMapper<Tag>{
    List<Tag> selectByTagId(String ArticleId);

    List<Tag> selectHots(int limit);

    List<TagCount> selectTagCount();
}

package com.lbm.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lbm.api.dao.entity.Tag;
import com.lbm.api.vo.DetailTagVo;
import com.lbm.api.vo.Result;
import com.lbm.api.vo.TagVo;

import java.util.List;

public interface TagService extends IService<Tag> {
    /**
     * 根据文章ID查询标签集合
     * @param ArticleId 文章ID
     * @return
     */
    List<TagVo> selectByTagId(String ArticleId);

    /**
     * 查询最热标签(出现次数最多的标签)
     * @param limit 要显示最热标签的个数
     * @return
     */
    List<Tag> hots(int limit);

    List<Tag> findAllTags();

    List<DetailTagVo> findDetailTags();

    TagVo findTagById(String id);

    Result getTagCount();
}

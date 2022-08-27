package com.lbm.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lbm.admin.entity.ArticleTag;
import com.lbm.admin.entity.dos.TagCount;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lbm
 * @since 2022-01-27
 */
public interface ArticleTagService extends IService<ArticleTag> {

    Boolean addMap(List<String> tagIdList, String id);

    Boolean newArticleTagHandler(List<String> tagIdList, String id);

    Integer deleteTagByArticleId(String articleId);

    List<TagCount> getTagArticleCount();
}

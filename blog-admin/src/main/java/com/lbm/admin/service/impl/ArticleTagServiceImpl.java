package com.lbm.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbm.admin.entity.ArticleTag;
import com.lbm.admin.mapper.ArticleTagMapper;
import com.lbm.admin.service.ArticleTagService;
import com.lbm.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lbm
 * @since 2022-01-27
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {
    @Autowired
    ArticleTagMapper articleTagMapper;

    @Override
    public Boolean addMap(List<String> tagIdList, String articleId) {
        for (String tagId : tagIdList) {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleId(articleId);
            articleTag.setTagId(tagId);
            int res = this.articleTagMapper.insert(articleTag);
            if (res != 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 新文章标签处理器
     *
     * @param newTagIdList 新标签集合
     * @param articleId    文章ID
     */
    @Transactional
    @Override
    public Boolean newArticleTagHandler(List<String> newTagIdList, String articleId) {
        /**
         * 查询原文章已有的标签信息
         */
        LambdaQueryWrapper<ArticleTag> articleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleTagLambdaQueryWrapper.eq(ArticleTag::getArticleId, articleId);
        List<ArticleTag> oldArticleTagList = articleTagMapper.selectList(articleTagLambdaQueryWrapper);
        /**
         * 旧TagIdList在新tagIdList中不存在时，将旧ID删除
         */
        for (ArticleTag articleTag : oldArticleTagList) {
            if (!newTagIdList.contains(articleTag.getTagId())) {
                int res = articleTagMapper.deleteById(articleTag.getId());
                if (res != 1) {
                    return false;
                }
            }
        }
        /**
         * LIST转化
         */
        ArrayList<String> oldTagIdList = new ArrayList<>();
        oldArticleTagList.forEach(oldArticleTag -> {
            oldTagIdList.add(oldArticleTag.getTagId());
        });
        /**
         * 新ID在旧ID中不包含时，将新ID添加
         */
        for (String newTagId : newTagIdList) {
            if (!oldTagIdList.contains(newTagId)) {
                ArticleTag newArticleTag = new ArticleTag();
                newArticleTag.setTagId(newTagId);
                newArticleTag.setArticleId(articleId);
                int res = articleTagMapper.insert(newArticleTag);
                if (res != 1) {
                    return false;
                }
            }
        }
        return true;
    }
}

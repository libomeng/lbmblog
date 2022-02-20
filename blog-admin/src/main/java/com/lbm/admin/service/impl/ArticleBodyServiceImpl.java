package com.lbm.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.lbm.admin.entity.ArticleBody;
import com.lbm.admin.entity.params.CreateArticleParam;
import com.lbm.admin.mapper.ArticleBodyMapper;
import com.lbm.admin.service.ArticleBodyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbm.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ArticleBodyServiceImpl extends ServiceImpl<ArticleBodyMapper, ArticleBody> implements ArticleBodyService {
    @Autowired
    ArticleBodyMapper articleBodyMapper;
    /**
     *  根据前端传回的参数进行文章体的创建
     * @param articleParam 创建文章的参数
     * @param articleId 创建文章ID
     * @return
     */
    @Override
    public String createArticleBody(CreateArticleParam articleParam,String articleId) {
        ArticleBody articleBody = new ArticleBody();
        articleBody.setArticleId(articleId);
        articleBody.setContent(articleParam.getValue());
        articleBody.setContentHtml(articleParam.getHtml());
        articleBody.setWord(articleParam.getWords());
        articleBody.setRedingTime(articleParam.getReadTime());
        int res = articleBodyMapper.insert(articleBody);
        if(res !=1) {
            return null;
        }
        return articleBody.getId();
    }

    @Override
    public Boolean updateArticleBody(CreateArticleParam articleParam) {
        ArticleBody articleBody = new ArticleBody();
        articleBody.setArticleId(articleParam.getId());
        articleBody.setContent(articleParam.getValue());
        articleBody.setContentHtml(articleParam.getHtml());
        articleBody.setWord(articleParam.getWords());
        articleBody.setRedingTime(articleParam.getReadTime());
        LambdaUpdateWrapper<ArticleBody> articleBodyLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        articleBodyLambdaUpdateWrapper.eq(ArticleBody::getArticleId, articleParam.getId());
        int res = articleBodyMapper.update(articleBody, articleBodyLambdaUpdateWrapper);
        if (res != 1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteArticleBody(String bodyId) {
        int res =this.articleBodyMapper.deleteArticleBody(bodyId);
        if(res ==1){
            return true;
        }
        return false;
    }
}

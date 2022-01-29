package com.lbm.admin.service;

import com.lbm.admin.entity.ArticleBody;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lbm.admin.entity.params.CreateArticleParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lbm
 * @since 2022-01-25
 */
public interface ArticleBodyService extends IService<ArticleBody> {

    String createArticleBody(CreateArticleParam articleParam, String id);

    Boolean updateArticleBody(CreateArticleParam articleParam);
}

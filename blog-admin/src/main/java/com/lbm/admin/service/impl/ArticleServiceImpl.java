package com.lbm.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbm.admin.entity.Article;
import com.lbm.admin.entity.vo.ArticleListVo;
import com.lbm.admin.mapper.ArticleMapper;
import com.lbm.admin.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Override
    public Page<ArticleListVo> pageArticleVo(Page<ArticleListVo> articlePage) {
       Page<ArticleListVo> page = articleMapper.pageArticleVo(articlePage);
        return page;
    }
}

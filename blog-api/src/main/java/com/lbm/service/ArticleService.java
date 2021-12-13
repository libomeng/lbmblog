package com.lbm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lbm.dao.dos.Archives;
import com.lbm.dao.entity.Article;
import com.lbm.vo.ArticleVo;
import com.lbm.vo.HotArticleVo;
import com.lbm.vo.Result;
import com.lbm.vo.params.ArticleParam;
import com.lbm.vo.params.PageParams;

import java.util.List;

public interface ArticleService extends IService<Article> {
    /**
     * 分页查询文章列表
     * @param pageParams
     * @return
     */
    List<ArticleVo> findArticles(PageParams pageParams);

    /**
     *  查询最热文章列表
     * @param Limit 查询个数
     * @return
     */
    List<HotArticleVo> listHotArticles(Integer Limit);

    /**
     * 查询最新文章
     * @param limit 查询个数
     * @return
     */
    List<ArticleVo> listNewArticles(int limit);

    /**
     * 获取文章创建时间的归档
     * @return
     */
    List<Archives> getArchivesCreateTimeList();

    /**
     * 根据ID查询文章
     * @param id
     * @return
     */
    ArticleVo findArticlesById(Long id);


    /**
     * 新增文章
     * @param articleParam
     * @return
     */
    Long  createArticle(ArticleParam articleParam);
}

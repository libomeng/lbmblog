package com.lbm.api.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbm.api.dao.dos.Archives;
import com.lbm.api.dao.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper extends BaseMapper<Article> {
    List<Article> selectHotArticle(int limit);

    List<Archives> getArchives();

    IPage<Article> listArticle(Page<Article> page, @Param("tagId")Long tagId, @Param("categoryId")Long categoryId, @Param("year")String year, @Param("month")String month);

    List<Article> getSimpleList(@Param("tagId")String tagId,@Param("categoryId")String categoryId);

    Integer articleCount();
}

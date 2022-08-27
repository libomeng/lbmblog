package com.lbm.api.dao.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lbm.api.dao.dos.ArticleCountForCategory;
import com.lbm.api.dao.entity.Category;

import java.util.List;

public interface CategoryMapper extends BaseMapper<Category> {
    List<ArticleCountForCategory> getAllArticleCountForCategory();
}

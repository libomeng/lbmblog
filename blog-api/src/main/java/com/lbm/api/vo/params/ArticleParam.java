package com.lbm.api.vo.params;

import com.lbm.api.vo.ArticleBodyParam;
import com.lbm.api.vo.CategoryVo;
import com.lbm.api.vo.TagVo;
import lombok.Data;

import java.util.List;
@Data
public class ArticleParam {

    private String id;

    private ArticleBodyParam body;

    private CategoryVo category;

    private String summary;

    private List<TagVo> tags;

    private String title;


}

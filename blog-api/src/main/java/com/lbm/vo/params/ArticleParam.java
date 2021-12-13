package com.lbm.vo.params;

import com.lbm.vo.ArticleBodyParam;
import com.lbm.vo.CategoryVo;
import com.lbm.vo.TagVo;
import lombok.Data;

import java.util.List;
@Data
public class ArticleParam {

    private Long id;

    private ArticleBodyParam body;

    private CategoryVo category;

    private String summary;

    private List<TagVo> tags;

    private String title;


}

package com.lbm.api.vo;

import com.lbm.api.dao.entity.Article;
import lombok.Data;

import java.util.Date;

@Data
public class NewArticleVo {
    private String id;
    private String title;
    private Date gmtCreate;
    public NewArticleVo(){

    }
    public NewArticleVo(Article article){
        this.id=article.getId();
        this.title=article.getTitle();
        this.gmtCreate = article.getGmtCreate();
    }
}

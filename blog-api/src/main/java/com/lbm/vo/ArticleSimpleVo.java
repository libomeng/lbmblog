package com.lbm.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.lbm.dao.entity.Article;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ArticleSimpleVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String title;
    private String createDate;

    public ArticleSimpleVo(){}
    public ArticleSimpleVo(Long id,String title,Long createDate){
        this.id=id;
        this.title= title;
        this.createDate= new DateTime(createDate).toString("yyyy-MM-dd HH:mm:ss");
    }

    public static ArticleSimpleVo toVo(Article article){
        ArticleSimpleVo articleSimpleVo = new ArticleSimpleVo(article.getId(),article.getTitle(), article.getCreateDate());
        return articleSimpleVo;
    }

    public static List<ArticleSimpleVo> copyList(List<Article> articleList){
        List<ArticleSimpleVo> articleSimpleVoList = new ArrayList<>();
        for (Article article : articleList) {
            ArticleSimpleVo articleSimpleVo = ArticleSimpleVo.toVo(article);
            articleSimpleVoList.add(articleSimpleVo);
        }
        return articleSimpleVoList;
    }
}

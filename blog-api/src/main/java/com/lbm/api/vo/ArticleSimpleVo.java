package com.lbm.api.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.lbm.api.dao.entity.Article;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ArticleSimpleVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private String id;
    private String title;
    private Date gmtCreate;

    public ArticleSimpleVo(){}
    public ArticleSimpleVo(String id,String title,Date createDate){
        this.id=id;
        this.title= title;
        this.gmtCreate= createDate;
    }

    public static ArticleSimpleVo toVo(Article article){
        ArticleSimpleVo articleSimpleVo = new ArticleSimpleVo(article.getId(),article.getTitle(), article.getGmtCreate());
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

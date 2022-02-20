package com.lbm.api.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ArticleVo {
    private String id;

    private String title;

    private String summary;

    private String img;

    private Integer commentCounts;

    private Integer viewCounts;

    private Integer weight;
    /**
     * 创建时间
     */
    private Date GmtCreate;

    private String author;

    private ArticleBodyVo body;

    private List<TagVo> tags;

    private CategoryVo category;

    @ApiModelProperty(value = "是否置顶")
    private Integer isWeight;

    @ApiModelProperty(value = "是否发布 1=发布  0=未发布")
    private Integer isPublish;

    @ApiModelProperty(value = "是否开启评论 1=开启 0=未开启")
    private Integer isComment;
}

package com.lbm.admin.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author lbm
 * @since 2022-01-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bm_article_body")
@ApiModel(value="ArticleBody对象", description="")
public class ArticleBody implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "文章内容HTML格式")
    private String contentHtml;

    @ApiModelProperty(value = "文章字数")
    private Integer word;

    @ApiModelProperty(value = "阅读时长 单位：分钟")
    private Integer redingTime;

    @ApiModelProperty(value = "文章id")
    private String articleId;

    @TableLogic
    @ApiModelProperty(value = "逻辑删除 1=删除  0=未删除")
    private Integer isDeleted;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    private Date gmtModified;


}

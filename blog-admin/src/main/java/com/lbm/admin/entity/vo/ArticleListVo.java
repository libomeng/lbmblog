package com.lbm.admin.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.lbm.admin.entity.Article;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

@Data
public class ArticleListVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "评论数量")
    private Integer commentCounts;

    @ApiModelProperty(value = "简介")
    private String summary;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "浏览数量")
    private Integer viewCounts;

    @ApiModelProperty(value = "是否置顶")
    private Integer isWeight;

    @ApiModelProperty(value = "是否发布 1=发布  0=未发布")
    private Integer isPublish;

    @ApiModelProperty(value = "是否开启评论 1=开启 0=未开启")
    private Integer isComment;

    @ApiModelProperty(value = "内容")
    private String articleBody;

    @ApiModelProperty(value = "类别")
    private String category;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间")
    private Date gmtModified;

}

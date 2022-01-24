package com.lbm.api.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author lbm
 * @since 2022-01-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bm_comment")
@ApiModel(value="Comment对象", description="")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "文章ID")
    private String articleId;

    @ApiModelProperty(value = "作者ID")
    private String authorId;

    @ApiModelProperty(value = "父评论ID")
    private String parentId;

    @ApiModelProperty(value = "被评论ID")
    private String toUid;

    @ApiModelProperty(value = "等级")
    private Integer level;

    @TableLogic
    @ApiModelProperty(value = "是否删除")
    private Integer isDeleted;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "修改时间")
    private Date gmtModified;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;
}

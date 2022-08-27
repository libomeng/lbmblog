package com.lbm.admin.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

import com.lbm.admin.entity.vo.TagSelectorVo;
import com.lbm.admin.entity.vo.TagVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

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
@TableName("bm_tag")
@ApiModel(value="Tag对象", description="")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "图标")
    private String avatar;

    @ApiModelProperty(value = "标签名称")
    private String tagName;

    @TableLogic
    @ApiModelProperty(value = "逻辑删除 1=删除 0=未删除")
    private Integer isDeleted;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    private Date gmtModified;

    public static TagSelectorVo toSelectorVo(Tag tag){
        TagSelectorVo tagSelectorVo= new TagSelectorVo();
        tagSelectorVo.setId(tag.getId());
        tagSelectorVo.setLabel(tag.getTagName());
        tagSelectorVo.setValue(tag.getId());
        return tagSelectorVo;
    }

    public static List<TagSelectorVo> copyList(List<Tag> tagList){
        List<TagSelectorVo> tagSelectorVos = new ArrayList<>();
        tagList.forEach((tag -> {
            TagSelectorVo tagSelectorVo = toSelectorVo(tag);
            tagSelectorVos.add(tagSelectorVo);
        }));
        return tagSelectorVos;
    }

    public static TagVo toVo(Tag tag){
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag,tagVo);
        return tagVo;
    }

}

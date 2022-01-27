package com.lbm.admin.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

import com.lbm.admin.entity.vo.CategorySelectorVo;
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
@TableName("bm_category")
@ApiModel(value="Category对象", description="")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "作者")
    private String avatar;

    @ApiModelProperty(value = "分类名称")
    private String categoryName;

    @ApiModelProperty(value = "描述")
    private String description;

    @TableLogic
    @ApiModelProperty(value = "逻辑删除 1=删除  0=未删除")
    private Integer isDeleted;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    private Date gmtModified;

    public static CategorySelectorVo toCategorySelectorVo(Category category){
        CategorySelectorVo categorySelectorVo = new CategorySelectorVo();
        categorySelectorVo.setId(category.getId());
        categorySelectorVo.setLabel(category.getCategoryName());
        categorySelectorVo.setDescription(category.getDescription());
        categorySelectorVo.setValue(category.getId());
        return categorySelectorVo;
    }
    public static List<CategorySelectorVo> copyList(List<Category> categoryList){
        List<CategorySelectorVo> categorySelectorVoList =new ArrayList<>();
        categoryList.forEach(category -> {
            CategorySelectorVo categorySelectorVo = toCategorySelectorVo(category);
            categorySelectorVoList.add(categorySelectorVo);
        });
        return categorySelectorVoList;
    }
}

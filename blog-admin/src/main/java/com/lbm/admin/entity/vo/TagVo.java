package com.lbm.admin.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TagVo {
    private String id;


    private String avatar;


    private String tagName;


    private Date gmtCreate;

    private Date gmtModified;
    private  Integer articleCount ; //标签下的文章数量

}

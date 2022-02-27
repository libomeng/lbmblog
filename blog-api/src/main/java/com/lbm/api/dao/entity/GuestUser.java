package com.lbm.api.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

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
 * @since 2022-02-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bm_guest_user")
@ApiModel(value="GuestUser对象", description="")
public class GuestUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String nickName;

    private String email;

    private String ip;

    private String cityName;

    private Date lastTime;
}

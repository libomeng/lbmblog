package com.lbm.api.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * UserVo 用于前端展示用户信息
 */
@Data
public class UserVo {
    private String id;

    private String ip;

    private String nickName;

    private String cityName;

    private String email;

    private String avatar;

    private Date lastTime;
}

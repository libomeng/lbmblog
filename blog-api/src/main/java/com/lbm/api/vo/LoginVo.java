package com.lbm.api.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.lbm.api.dao.entity.GuestUser;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class LoginVo {

    private String id;
    private String ip;
    private String nickName;
    private String cityName;
    private String email;

    public LoginVo() {
    }

    public LoginVo(GuestUser user) {
        this.id = user.getId();
        this.ip = user.getIp();
        this.cityName = user.getCityName();
        this.nickName = user.getNickName();
        this.email = user.getEmail();
    }
}

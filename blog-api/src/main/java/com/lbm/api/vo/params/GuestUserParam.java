package com.lbm.api.vo.params;

import lombok.Data;

@Data
public class GuestUserParam {
    private String id;
    private String ip;
    private String cityName;
    private String nickName;
    private String email;
}

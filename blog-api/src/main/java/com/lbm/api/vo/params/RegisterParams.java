package com.lbm.api.vo.params;

import lombok.Data;

@Data
public class RegisterParams {
    private String account;
    private String password;
    private String nickname;

}

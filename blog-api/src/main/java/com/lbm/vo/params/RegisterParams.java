package com.lbm.vo.params;

import com.lbm.vo.Result;
import lombok.Data;

@Data
public class RegisterParams {
    private String account;
    private String password;
    private String nickname;

}

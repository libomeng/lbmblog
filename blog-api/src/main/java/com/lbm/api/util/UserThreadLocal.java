package com.lbm.api.util;

import com.lbm.api.vo.LoginVo;

public class UserThreadLocal {
    private static final ThreadLocal<LoginVo> LOCAL =new ThreadLocal<>();

    public static void put(LoginVo loginVo){
        LOCAL.set(loginVo);
    }

    public static LoginVo get(){
        LoginVo loginVo = LOCAL.get();
        return loginVo;
    }

    public  static void  remove(){
        LOCAL.remove();
    }
}

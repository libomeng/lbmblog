package com.lbm.api.util;


import com.lbm.api.dao.entity.GuestUser;

public class UserThreadLocal {
    private static final ThreadLocal<GuestUser> LOCAL =new ThreadLocal<>();

    public static void put(GuestUser guestUser){
        LOCAL.set(guestUser);
    }

    public static GuestUser get(){
        GuestUser guestUser = LOCAL.get();
        return guestUser;
    }

    public  static void  remove(){
        LOCAL.remove();
    }
}

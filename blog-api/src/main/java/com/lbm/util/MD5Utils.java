package com.lbm.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {
    private static final String slat="lbmlbmlbm3343";
    public static String passwordToMD5(String password,String slat){
        return DigestUtils.md5Hex(password+slat);
    }
    public static String passwordToMD5(String password){
        return DigestUtils.md5Hex(password+slat);
    }


    public static void main(String[] args) {
        String password = passwordToMD5("807073364", "lbmlbmlbm3343");
        System.out.println(password);
    }
}

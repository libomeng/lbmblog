package com.lbm.common.uitl;

import java.lang.reflect.Field;

public class ObjectUtil {
    public static Boolean checkObjFieldIsNull(Object obj) {
        for(Field f : obj.getClass().getDeclaredFields()){
            f.setAccessible(true);
            try {
                if(f.get(obj) == null){
                    return true;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}

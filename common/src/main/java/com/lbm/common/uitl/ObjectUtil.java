package com.lbm.common.uitl;

import java.lang.reflect.Field;

public class ObjectUtil {
    /**
     * Object工具类，用于检查对象中是否有空属性
     * @param obj 对象
     * @return true=有空属性 false=无空属性
     */
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

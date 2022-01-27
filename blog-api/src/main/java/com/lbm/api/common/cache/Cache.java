package com.lbm.common.cache;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {
    String name() default "";
    long expire() default 5*60*1000;
}

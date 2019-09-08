package com.iccm.common.annotation;



import java.lang.annotation.*;

/**
 * Created by Administrator on 2019/9/7.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPermissions {
    String value() default "";

    String authorities() default "";
}

package com.iccm.common.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2019/9/8.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface PermissionsApi {

    String value() default "";

    String authorities() default "";
}

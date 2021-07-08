package com.ruoyi.common.annotation;

import java.lang.annotation.*;

/**
 * @author Administrator
 * @description: TODO
 * @date 2021/7/5 0005 16:46
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataUserScope {
    String value() default "";
}

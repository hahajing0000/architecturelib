package com.zy.corelib.mvp.presenter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author:zhangyue
 * @date:2020/11/19
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public  @interface Presenter {
    String name() default "";
}

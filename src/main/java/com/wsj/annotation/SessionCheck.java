package com.wsj.annotation;

import org.apache.catalina.Manager;

import java.lang.annotation.*;

/**
 * Created by jimmy on 2017/6/25.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SessionCheck {
    enum Type{MANAGER,WEB};
    boolean checked() default true;
    Type checkedType() default Type.MANAGER;

}

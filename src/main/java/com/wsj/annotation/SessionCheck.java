package com.wsj.annotation;

import java.lang.annotation.*;

/**
 * Created by jimmy on 2017/6/25.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SessionCheck {
    public enum Type{MANAGER,WEB};
    public boolean checked() default true;

}

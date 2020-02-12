package com.xqc.xplan.xspart.aop.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BuryingPoint {

    String value() default "";
}

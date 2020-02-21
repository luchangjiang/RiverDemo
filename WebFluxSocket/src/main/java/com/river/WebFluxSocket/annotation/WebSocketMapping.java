package com.river.WebFluxSocket.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2019-08-17 18:37
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface WebSocketMapping {
    String value() default "";
}

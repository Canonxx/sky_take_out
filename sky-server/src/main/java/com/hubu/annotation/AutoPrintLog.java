package com.hubu.annotation;/*
 * @Auther:long
 * @Date:2025/11/5
 * @Description:
 * @VERSON:1.8
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动打印日志注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AutoPrintLog {

}

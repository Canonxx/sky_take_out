package com.hubu.aspect;/*
 * @Auther:long
 * @Date:2025/11/5
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.annotation.AutoPrintLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Component
@Aspect
@Slf4j
public class AutoPrintLogAspect {
    @Pointcut("execution(* com.hubu.controller.admin.*.*(..)) && @annotation(com.hubu.annotation.AutoPrintLog)")
    public void autoPrintLog(){

    }
    @Before("autoPrintLog()")
    public void beforeInform(JoinPoint joinPoint){
        log.info("开始自动日志打印");
        //获得连接点对方法的签名
        MethodSignature signature =(MethodSignature) joinPoint.getSignature();
        // 获取方法
        Method method = signature.getMethod();
        // 获取方法上的形参
        Parameter[] parameters = method.getParameters();
        // 获取实体对象
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            log.info("请求参数{}：{}",parameters[i].toString(),args[i]);
        }
    }
}

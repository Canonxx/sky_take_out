package com.hubu.aspect;/*
 * @Auther:long
 * @Date:2025/10/16
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.annotation.AutoFill;
import com.hubu.constant.AutoFillConstant;
import com.hubu.context.BaseContext;
import com.hubu.enumeration.AutoFillEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Component
@Aspect
@Slf4j
public class AutoFillAspect {
    /**
     * 自动填充切点
     */
    @Pointcut("execution(* com.hubu.mapper.*.*(..)) && @annotation(com.hubu.annotation.AutoFill)")
    public void autoFillPointCut(){

    }

    /**
     * 前置通知
     */
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint){
        log.info("开始自动填充");
        MethodSignature  signature=(MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        AutoFill annotation = method.getAnnotation(AutoFill.class);
        // 获取注解的参数值
        AutoFillEnum value = annotation.value();
        // 获取切入方法的参数对象 实参
        Object[] args = joinPoint.getArgs();
        Object entity = args[0];//获取实体对象

        //准备赋值的数据
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();

        if (value == AutoFillEnum.INSERT){
            try {
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                setCreateTime.invoke(entity, now);
                setUpdateTime.invoke(entity, now);
                setCreateUser.invoke(entity, currentId);
                setUpdateUser.invoke(entity, currentId);
            }
            catch (Exception e){
                log.error("实体自动填充失败{}，失败原因{}",entity.getClass().getName(),e.getMessage());
            }
        } else if (value == AutoFillEnum.UPDATE) {
            try {
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, currentId);
            }
            catch (Exception e){
                log.error("实体自动填充失败{}，失败原因{}",entity.getClass().getName(),e.getMessage());
            }
        }
    }
}

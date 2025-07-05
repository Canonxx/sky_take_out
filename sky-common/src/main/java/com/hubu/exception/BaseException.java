package com.hubu.exception;/*
 * @Auther:long
 * @Date:2025/7/5
 * @Description:基础异常类
 * @VERSON:1.8
 */

public class BaseException extends RuntimeException{
    public BaseException(){}
    public BaseException(String message){
        super(message);
    }

}

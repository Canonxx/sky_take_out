package com.hubu.exception;/*
 * @Auther:long
 * @Date:2025/7/5
 * @Description:密码错误异常
 * @VERSON:1.8
 */

public class PasswordErrorException extends BaseException{
    public PasswordErrorException(){}
    public PasswordErrorException(String message){
        super(message);
    }
}

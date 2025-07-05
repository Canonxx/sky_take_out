package com.hubu.exception;/*
 * @Auther:long
 * @Date:2025/7/5
 * @Description:账户不存在异常类
 * @VERSON:1.8
 */

public class AccountNotFoundException extends BaseException{
    public AccountNotFoundException(){}
    public AccountNotFoundException(String message){
        super(message);
    }
}

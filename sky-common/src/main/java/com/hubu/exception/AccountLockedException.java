package com.hubu.exception;/*
 * @Auther:long
 * @Date:2025/7/5
 * @Description:账户锁定异常类
 * @VERSON:1.8
 */

public class AccountLockedException extends BaseException{
    public AccountLockedException(){}
    public AccountLockedException(String message){
        super(message);
    }
}

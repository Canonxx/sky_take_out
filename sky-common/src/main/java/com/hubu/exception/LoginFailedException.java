package com.hubu.exception;/*
 * @Auther:long
 * @Date:2026/3/8
 * @Description:
 * @VERSON:1.8
 */

public class LoginFailedException extends BaseException{
    public LoginFailedException(){}
    public LoginFailedException(String msg){
        super(msg);
    }
}

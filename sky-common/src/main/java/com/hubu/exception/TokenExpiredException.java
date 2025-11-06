package com.hubu.exception;/*
 * @Auther:long
 * @Date:2025/11/6
 * @Description:
 * @VERSON:1.8
 */

public class TokenExpiredException extends BaseException{
    public TokenExpiredException(){

    }
    public TokenExpiredException(String msg){
        super(msg);
    }
}

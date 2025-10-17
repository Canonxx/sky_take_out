package com.hubu.exception;/*
 * @Auther:long
 * @Date:2025/10/15
 * @Description:
 * @VERSON:1.8
 */

public class DeletionNotAllowedException extends BaseException{
    public DeletionNotAllowedException(){

    }
    public DeletionNotAllowedException(String msg){
        super(msg);
    }
}

package com.hubu.exception;/*
 * @Auther:long
 * @Date:2025/11/6
 * @Description:
 * @VERSON:1.8
 */

import com.fasterxml.jackson.databind.ser.Serializers;

public class SessionInvalidException extends BaseException {
    public SessionInvalidException(){

    }
    public SessionInvalidException(String msg){
        super(msg);
    }
}

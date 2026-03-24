package com.hubu.exception;/*
 * @Auther:long
 * @Date:2026/3/23
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.context.BaseContext;

public class AddressBookBusinessException extends BaseException {
    public AddressBookBusinessException(String msg) {
        super(msg);
    }
    public AddressBookBusinessException() {

    }
}

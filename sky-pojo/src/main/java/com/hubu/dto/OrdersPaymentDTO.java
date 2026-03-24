package com.hubu.dto;/*
 * @Auther:long
 * @Date:2026/3/24
 * @Description:
 * @VERSON:1.8
 */

import lombok.Data;

import java.io.Serializable;

@Data
public class OrdersPaymentDTO implements Serializable {
    //订单号
    private String orderNumber;

    //付款方式
    private Integer payMethod;

}
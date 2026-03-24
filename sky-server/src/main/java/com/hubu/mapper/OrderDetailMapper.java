package com.hubu.mapper;/*
 * @Auther:long
 * @Date:2026/3/24
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.entity.OrderDetail;

import java.util.ArrayList;

public interface OrderDetailMapper {
    void insertBatch(ArrayList<OrderDetail> orderDetailList);
}

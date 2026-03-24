package com.hubu.mapper;/*
 * @Auther:long
 * @Date:2026/3/24
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.entity.Orders;

public interface OrderMapper {
    void save(Orders order);

    Orders getByNumber(String outTradeNo);

    void update(Orders orders);
}

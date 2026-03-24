package com.hubu.service.admin;/*
 * @Auther:long
 * @Date:2026/3/23
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.dto.OrderDTO;
import com.hubu.dto.OrdersPaymentDTO;
import com.hubu.vo.OrderPaymentVO;
import com.hubu.vo.OrderVO;

public interface OrderService {
    OrderVO save(OrderDTO orderDTO);

    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO);
}

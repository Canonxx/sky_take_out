package com.hubu.controller.user;/*
 * @Auther:long
 * @Date:2026/3/23
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.dto.OrderDTO;
import com.hubu.dto.OrdersPaymentDTO;
import com.hubu.result.Result;
import com.hubu.service.admin.OrderService;
import com.hubu.vo.OrderPaymentVO;
import com.hubu.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("userOrderController")
@RequestMapping("user/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 用户下单
     * @param orderDTO
     * @return
     */
    @PostMapping("/submit")
    public Result<OrderVO> save(@RequestBody OrderDTO orderDTO){
        log.info("用户下单{}",orderDTO);
        OrderVO orderVO = orderService.save(orderDTO);
        return Result.success(orderVO);
    }
    /**
     * 订单支付
     *
     * @param ordersPaymentDTO
     * @return
     */
    @PutMapping("/payment")
    public Result<OrderPaymentVO> payment(@RequestBody OrdersPaymentDTO ordersPaymentDTO) throws Exception {
        log.info("订单支付：{}", ordersPaymentDTO);
        OrderPaymentVO orderPaymentVO = orderService.payment(ordersPaymentDTO);
        log.info("生成预支付交易单：{}", orderPaymentVO);
        return Result.success(orderPaymentVO);
    }
}

package com.hubu.service.admin.impl;/*
 * @Auther:long
 * @Date:2026/3/23
 * @Description:
 * @VERSON:1.8
 */

import com.alibaba.fastjson.JSON;
import com.hubu.constant.MessageConstant;
import com.hubu.context.BaseContext;
import com.hubu.dto.OrderDTO;
import com.hubu.dto.OrdersPaymentDTO;
import com.hubu.entity.AddressBook;
import com.hubu.entity.OrderDetail;
import com.hubu.entity.Orders;
import com.hubu.entity.ShoppingCart;
import com.hubu.exception.AddressBookBusinessException;
import com.hubu.mapper.AddressBookMapper;
import com.hubu.mapper.OrderDetailMapper;
import com.hubu.mapper.OrderMapper;
import com.hubu.mapper.ShoppingCartMapper;
import com.hubu.service.admin.OrderService;
import com.hubu.service.user.ShoppingCartService;
import com.hubu.vo.OrderPaymentVO;
import com.hubu.vo.OrderVO;
import com.hubu.websocket.WebSocketServer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AddressBookMapper addressBookMapper;
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private WebSocketServer webSocketServer;
    @Transactional
    @Override
    public OrderVO save(OrderDTO orderDTO) {
        //异常情况的处理（收货地址为空、超出配送范围、购物车为空）
        Long addressBookId = orderDTO.getAddressBookId();
        AddressBook addressBook = addressBookMapper.getById(addressBookId);
        if (addressBook == null){// 地址为空
            throw new AddressBookBusinessException(MessageConstant.ADDRESS_BOOK_IS_NULL);
        }
        ShoppingCart shoppingCart = ShoppingCart.builder().userId(BaseContext.getCurrentId()).build();
        List<ShoppingCart> shoppingCartList = shoppingCartMapper.query(shoppingCart);
        if (shoppingCartList==null || shoppingCartList.isEmpty()){
            throw new AddressBookBusinessException(MessageConstant.SHOPPING_CART_IS_NULL);
        }
        // 1. 创建订单 2.创建详细订单 一个订单可能对应多个详细订单
        Orders order = new Orders().builder()
                .number(String.valueOf(System.currentTimeMillis()))
                .status(Orders.PENDING_PAYMENT)
                .userId(BaseContext.getCurrentId())
                .orderTime(LocalDateTime.now())
                .payStatus(Orders.UNPAID)
                .phone(addressBook.getPhone())
                .address(addressBook.getDetail())
                .consignee(addressBook.getConsignee())
                .build();
        BeanUtils.copyProperties(orderDTO, order);
        // 保存用户订单
        orderMapper.save(order);
        // 保存用户详细订单 详细订单里的字段都是冗余字段 来自shoppingcart
        ArrayList<OrderDetail> orderDetailList = new ArrayList<>();
        shoppingCartList.forEach(shoppingCart1 -> {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(shoppingCart1,orderDetail);
            orderDetail.setOrderId(order.getId());
            orderDetailList.add(orderDetail);
        });
        orderDetailMapper.insertBatch(orderDetailList);
        // 删除用户购物车
        ShoppingCart userShoppingCart = new ShoppingCart();
        userShoppingCart.setUserId(BaseContext.getCurrentId());
        shoppingCartMapper.delete(userShoppingCart);
        OrderVO orderVO = new OrderVO();
        orderVO.setId(order.getId());
        orderVO.setOrderAmount(order.getAmount());
        orderVO.setOrderNumber(order.getNumber());
        orderVO.setOrderTime(order.getOrderTime());
        return orderVO;
    }


    /**
     * 订单支付
     *
     * @param ordersPaymentDTO
     * @return
     */
    public OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO){
        // 当前登录用户id
        // Long userId = BaseContext.getCurrentId();
        // User user = userMapper.getById(userId);

        // 直接调用paySuccess方法，模拟支付成功
        paySuccess(ordersPaymentDTO.getOrderNumber());

        // 调用微信支付接口，生成预支付交易单
        // JSONObject jsonObject = weChatPayUtil.pay(
        // ordersPaymentDTO.getOrderNumber(), // 商户订单号
        // new BigDecimal(0.01), // 支付金额，单位 元
        // "苍穹外卖订单", // 商品描述
        // user.getOpenid() // 微信用户的openid
        // );

        // if (jsonObject.getString("code") != null &&
        // jsonObject.getString("code").equals("ORDERPAID")) {
        // throw new OrderBusinessException("该订单已支付");
        // }

        // OrderPaymentVO vo = jsonObject.toJavaObject(OrderPaymentVO.class);
        // vo.setPackageStr(jsonObject.getString("package"));

        // return vo;
        return null;
    }
    /**
     * 支付成功，修改订单状态
     *
     * @param outTradeNo
     */
    public void paySuccess(String outTradeNo) {

        // 根据订单号查询订单
        Orders ordersDB = orderMapper.getByNumber(outTradeNo);

        // 根据订单id更新订单的状态、支付方式、支付状态、结账时间
        Orders orders = Orders.builder()
                .id(ordersDB.getId())
                .status(Orders.PENDING_ORDER)
                .payStatus(Orders.PAID)
                .checkoutTime(LocalDateTime.now())
                .build();

        orderMapper.update(orders);

        // 通过websocket通知商家
        Map<String, Object> map = new HashMap<>();
        map.put("type", 1); // 1:来单通知
        map.put("orderId", ordersDB.getId());
        map.put("content", "订单号: " + outTradeNo);
        String msg = JSON.toJSONString(map);
        webSocketServer.sendToAllClient(msg);
    }
}

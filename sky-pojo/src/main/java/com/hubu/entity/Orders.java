package com.hubu.entity;/*
 * @Auther:long
 * @Date:2026/3/23
 * @Description:
 * @VERSON:1.8
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders implements Serializable {
    // 订单状态常量
    public static final Integer PENDING_PAYMENT = 1;      // 待付款
    public static final Integer PENDING_ORDER = 2;        // 待接单
    public static final Integer ORDER_RECEIVED = 3;       // 已接单
    public static final Integer DELIVERING = 4;           // 派送中
    public static final Integer COMPLETED = 5;            // 已完成
    public static final Integer CANCELLED = 6;            // 已取消
    public static final Integer REFUND = 7;               // 退款

    // 支付状态常量
    public static final Integer UNPAID = 0;               // 未支付
    public static final Integer PAID = 1;                 // 已支付
    public static final Integer REFUNDED = 2;             // 退款

    // 主键
    private Long id;

    // 订单号
    private String number;

    // 订单状态 1待付款 2待接单 3已接单 4派送中 5已完成 6已取消 7退款
    private Integer status;

    // 下单用户
    private Long userId;

    // 地址id
    private Long addressBookId;

    // 下单时间
    private LocalDateTime orderTime;

    // 结账时间
    private LocalDateTime checkoutTime;

    // 支付方式 1微信,2支付宝
    private Integer payMethod;

    // 支付状态 0未支付 1已支付 2退款
    private Integer payStatus;

    // 实收金额
    private BigDecimal amount;

    // 备注
    private String remark;

    // 手机号
    private String phone;

    // 地址
    private String address;

    // 用户名称
    private String userName;

    // 收货人
    private String consignee;

    // 订单取消原因
    private String cancelReason;

    // 订单拒绝原因
    private String rejectionReason;

    // 订单取消时间
    private LocalDateTime cancelTime;

    // 预计送达时间
    private LocalDateTime estimatedDeliveryTime;

    // 配送状态  1立即送出  0选择具体时间
    private Integer deliveryStatus;

    // 送达时间
    private LocalDateTime deliveryTime;

    // 打包费
    private Integer packAmount;

    // 餐具数量
    private Integer tablewareNumber;

    // 餐具数量状态  1按餐量提供  0选择具体数量
    private Integer tablewareStatus;
}

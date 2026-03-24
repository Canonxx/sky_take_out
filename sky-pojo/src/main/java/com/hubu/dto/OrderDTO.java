package com.hubu.dto;/*
 * @Auther:long
 * @Date:2026/3/23
 * @Description:
 * @VERSON:1.8
 */

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Schema(description = "订单提交时传递的数据模型")
public class OrderDTO implements Serializable {

    @Schema(description = "地址簿id")
    private Long addressBookId;

    @Schema(description = "总金额")
    private BigDecimal amount;

    @Schema(description = "配送状态：1立即送出 0选择具体时间")
    private Integer deliveryStatus;

    @Schema(description = "预计送达时间")
    private String estimatedDeliveryTime;

    @Schema(description = "打包费")
    private Integer packAmount;

    @Schema(description = "付款方式：1微信 2支付宝")
    private Integer payMethod;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "餐具数量")
    private Integer tablewareNumber;

    @Schema(description = "餐具数量状态：1按餐量提供 0选择具体数量")
    private Integer tablewareStatus;
}

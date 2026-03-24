package com.hubu.vo;/*
 * @Auther:long
 * @Date:2026/3/23
 * @Description:
 * @VERSON:1.8
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "订单提交后返回的数据模型")
public class OrderVO implements Serializable {

    @Schema(description = "订单id")
    private Long id;

    @Schema(description = "订单金额")
    private BigDecimal orderAmount;

    @Schema(description = "订单号")
    private String orderNumber;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Schema(description = "下单时间")
    private LocalDateTime orderTime;
}

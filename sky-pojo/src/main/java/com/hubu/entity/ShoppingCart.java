package com.hubu.entity;/*
 * @Auther:long
 * @Date:2026/3/14
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
public class ShoppingCart implements Serializable {
    //shoppingcart表里面有具有冗余字段是为了方便减少多表查询
    // 购物车id
    private Long id;
    // 商品名称
    private String name;
    // 商品图片
    private String image;
    // 用户id
    private Long userId;
    // 菜品id
    private Long dishId;
    // 套餐id
    private Long setmealId;
    // 菜品口味
    private String dishFlavor;
    // 商品数量
    private Integer number;
    // 金额
    private BigDecimal amount;
    // 创建时间
    private LocalDateTime createTime;
}

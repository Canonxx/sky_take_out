package com.hubu.dto;/*
 * @Auther:long
 * @Date:2026/3/14
 * @Description:
 * @VERSON:1.8
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDTO implements Serializable {
    // 菜品id
    private Long dishId;
    // 套餐id
    private Long setmealId;
    // 口味
    private String dishFlavor;
}

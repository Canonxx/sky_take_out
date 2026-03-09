package com.hubu.entity;/*
 * @Auther:long
 * @Date:2025/11/11
 * @Description:
 * @VERSON:1.8
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetMealDish implements Serializable {
    // id
    private Long id;
    // 套餐id
    private Long setmealId;
    // 菜品id
    private Long dishId;
    // 菜品份数
    private Integer copies;
    private String name;
    private BigDecimal price;
}

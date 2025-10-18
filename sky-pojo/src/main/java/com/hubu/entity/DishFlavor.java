package com.hubu.entity;/*
 * @Auther:long
 * @Date:2025/10/18
 * @Description:
 * @VERSON:1.8
 */

import lombok.Data;

import java.io.Serializable;

@Data
public class DishFlavor implements Serializable {
    // 菜品口味id
    private Long id;
    // 菜品id
    private Long dishId;
    // 口味名称
    private String name;
    // 口味数据
    private String value;
}

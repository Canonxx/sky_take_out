package com.hubu.dto;/*
 * @Auther:long
 * @Date:2025/11/11
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.entity.SetMealDish;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class SetMealDTO implements Serializable {
    // 套餐id
    private Long id;
    // 套餐所在分类id
    private Long categoryId;
    // 名称
    private String name;
    // 价格
    private BigDecimal price;
    // 状态 在售或停售
    private Integer status;
    // 套餐描述
    private String description;
    // 图片
    private String image;
    // 套餐所关联的菜品
    private List<SetMealDish> setmealDishes = new ArrayList<>();
}

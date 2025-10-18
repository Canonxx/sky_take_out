package com.hubu.dto;/*
 * @Auther:long
 * @Date:2025/10/18
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.entity.DishFlavor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Schema(description = "添加菜品时传递的数据模型")
@Data
public class DishDTO implements Serializable {
    // 菜品id
    private Long id;
    // 菜品名称
    private String name;
    // 菜品分类id
    private Long categoryId;
    // 菜品价格
    private BigDecimal price;
    // 菜品图片
    private String image;
    // 菜品描述
    private String description;
    // 菜品状态
    private Integer status;
    // 菜品口味
    private List<DishFlavor> flavors = new ArrayList<>();
}

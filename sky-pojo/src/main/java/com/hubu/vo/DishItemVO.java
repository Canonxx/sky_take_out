package com.hubu.vo;/*
 * @Auther:long
 * @Date:2026/3/9
 * @Description:
 * @VERSON:1.8
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishItemVO {
    //菜品名称
    private String name;
    // 份数
    private Integer copies;
    // 菜品描述
    private String description;
    // 菜品图片
    private String image;
}

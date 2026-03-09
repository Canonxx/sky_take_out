package com.hubu.dto;/*
 * @Auther:long
 * @Date:2025/11/11
 * @Description:
 * @VERSON:1.8
 */

import lombok.Data;

/**
 * 套餐分页查询数据传输模型
 */
@Data
public class SetMealPageQueryDTO {
    // 页码大小
    private Integer pageSize;
    // 页码
    private Integer page;
    // 套餐名称
    private String name;
    // 套餐分类id
    private Integer categoryId;
    // 售卖状态
    private Integer status;

}

package com.hubu.vo;/*
 * @Auther:long
 * @Date:2025/11/11
 * @Description:
 * @VERSON:1.8
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 套餐分页查询返回的数据模型
 */
@Data
public class SetMealPageQueryVO {
    //id
    private Long id;
    // 套餐名称
    private String name;
    // 套餐图片
    private String image;
    // 套餐分类
    private Long categoryId;
    // 套餐价
    private BigDecimal price;
    // 售卖状态
    private Integer status;
    // 最后操作时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updateTime;
    // 分类名称
    private String categoryName;
}

package com.hubu.entity;/*
 * @Auther:long
 * @Date:2025/11/11
 * @Description:
 * @VERSON:1.8
 */

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class SetMeal implements Serializable {
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
    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createTime;
    // 更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updateTime;
    // 创建者
    private Long createUser;
    // 更新者
    private Long updateUser;
}

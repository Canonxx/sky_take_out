package com.hubu.vo;/*
 * @Auther:long
 * @Date:2025/10/19
 * @Description:
 * @VERSON:1.8
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hubu.entity.DishFlavor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishPageQueryVO implements Serializable {
    private Long id;
    //菜品名称
    private String name;
    //菜品分类id
    private Long categoryId;
    //菜品价格
    private BigDecimal price;
    //图片
    private String image;
    //描述信息
    private String description;
    //0 停售 1 起售
    private Integer status;
    //更新时间
    // todo 时间序列统一操作
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updateTime;
    //分类名称
    private String categoryName;
    //菜品关联的口味
//    private List<DishFlavor> flavors = new ArrayList<>();
}

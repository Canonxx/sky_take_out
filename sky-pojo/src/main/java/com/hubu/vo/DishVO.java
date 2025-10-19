package com.hubu.vo;/*
 * @Auther:long
 * @Date:2025/10/19
 * @Description:
 * @VERSON:1.8
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hubu.entity.DishFlavor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishVO implements Serializable {
    @Schema(description = "菜品id")
    private Long id;
    @Schema(description = "菜品名称")
    private String name;
    @Schema(description = "分类id")
    private Long categoryId;
    @Schema(description = "分类名称")
    private String categoryName;
    @Schema(description = "菜品价格")
    private BigDecimal price;
    @Schema(description = "图片")
    private String image;
    @Schema(description = "描述信息")
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updateTime;
    @Schema(description = "口味")
    private List<DishFlavor> flavors = new ArrayList<>();
    //0 停售 1 起售
    private Integer status;
}

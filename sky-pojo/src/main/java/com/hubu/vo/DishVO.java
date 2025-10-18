package com.hubu.vo;/*
 * @Auther:long
 * @Date:2025/10/17
 * @Description:
 * @VERSON:1.8
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    @Schema(description = "菜品状态")
    private Integer status;
    @Schema(description = "最近更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    // todo 时间格式化待解决
    private LocalDateTime updateTime;


}

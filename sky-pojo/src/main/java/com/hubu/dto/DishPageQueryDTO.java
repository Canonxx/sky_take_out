package com.hubu.dto;/*
 * @Auther:long
 * @Date:2025/10/18
 * @Description:
 * @VERSON:1.8
 */

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class DishPageQueryDTO implements Serializable {
    @Schema(description = "页码")
    private Integer page;
    @Schema(description = "页码大小")
    private Integer pageSize;
    @Schema(description = "菜品名称")
    private String name;
    @Schema(description = "菜品分类")
    private Long categoryId;
    @Schema(description = "菜品状态")
    private Integer status;
}

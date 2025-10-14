package com.hubu.dto;/*
 * @Auther:long
 * @Date:2025/10/14
 * @Description:
 * @VERSON:1.8
 */

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
@Schema(description = "添加分类时传递的数据模型")
@Data
public class CategoryDTO implements Serializable {
    @Schema(description = "分类id")
    private Long id;
    @Schema(description = "分类名称")
    private String name;
    @Schema(description = "排序")
    private Integer sort;
    @Schema(description = "分类类型 1为菜品分类，2为套餐分类")
    private Integer type;
}

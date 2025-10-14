package com.hubu.dto;/*
 * @Auther:long
 * @Date:2025/10/14
 * @Description:
 * @VERSON:1.8
 */

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
@Data
@Schema(description = "分类分页查询时传递的数据模型")
public class CategoryPageQueryDTO implements Serializable {
    @Schema(description = "页码")
    private Integer page;
    @Schema(description = "页码的大小")
    private Integer pageSize;
    @Schema(description = "分类名称")
    private String name;
    @Schema(description = "分类类型")
    private Integer type;
}

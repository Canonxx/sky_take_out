package com.hubu.dto;/*
 * @Auther:long
 * @Date:2025/10/12
 * @Description:
 * @VERSON:1.8
 */

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
@Data
@Schema(description = "员工分页查询时传递的数据模型")
public class EmployeePageQueryDTO implements Serializable {
    @Schema(description = "员工姓名",example = "张三")
    private String name;
    @Schema(description = "页码",example = "1")
    private int page;
    @Schema(description = "每页记录数",example = "10")
    private int pageSize;
}

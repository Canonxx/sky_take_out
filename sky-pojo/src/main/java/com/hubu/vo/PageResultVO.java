package com.hubu.vo;/*
 * @Auther:long
 * @Date:2025/10/12
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.entity.Employee;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "员工分页返回时的数据模型")
public class PageResultVO implements Serializable {
    @Schema(description = "总记录数")
    private Long total;
    @Schema(description = "当前页数据集合")
    private List records;
}

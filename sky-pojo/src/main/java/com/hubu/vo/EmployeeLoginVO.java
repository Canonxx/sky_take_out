package com.hubu.vo;/*
 * @Auther:long
 * @Date:2025/9/25
 * @Description:
 * @VERSON:1.8
 */

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "员工返回时传递的数据模型")
public class EmployeeLoginVO implements Serializable {
    @Schema(description = "主键值")
    private Integer id;
    @Schema(description = "姓名")
    private String name;
    @Schema(description = "Jwt令牌")
    private String token;
    @Schema(description = "用户名")
    private String userName;
}

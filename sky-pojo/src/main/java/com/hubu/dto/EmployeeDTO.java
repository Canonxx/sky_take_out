package com.hubu.dto;/*
 * @Auther:long
 * @Date:2025/10/11
 * @Description:
 * @VERSON:1.8
 */

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
@Data
@Schema(description = "新增员工时传递的数据模型")
public class EmployeeDTO implements Serializable {
    @Schema(description = "员工id")
    private Long id;
    @Schema(description = "身份证")
    private String idNumber;
    @Schema(description = "姓名")
    private String name;
    @Schema(description = "手机号")
    private String phone;
    @Schema(description = "性别")
    private String sex;
    @Schema(description = "用户名")
    private String username;
}

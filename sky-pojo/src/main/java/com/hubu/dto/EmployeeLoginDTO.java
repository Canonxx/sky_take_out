package com.hubu.dto;/*
 * @Auther:long
 * @Date:2025/7/2
 * @Description:员工登录时传递的数据模型
 * @VERSON:1.8
 */

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "员工登录时传递的数据模型")
public class EmployeeLoginDTO {
    /**
     * 账户
     */
    @Schema(description = "用户名")
    private String username;
    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;
}

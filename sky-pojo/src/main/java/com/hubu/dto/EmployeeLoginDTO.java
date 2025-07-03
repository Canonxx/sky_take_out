package com.hubu.dto;/*
 * @Auther:long
 * @Date:2025/7/2
 * @Description:员工登录时传递的数据模型
 * @VERSON:1.8
 */

import lombok.Data;

@Data
public class EmployeeLoginDTO {
    /**
     * 账户
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}

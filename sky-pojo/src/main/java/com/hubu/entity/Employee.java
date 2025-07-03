package com.hubu.entity;/*
 * @Auther:long
 * @Date:2025/7/2
 * @Description:员工实体类
 * @VERSON:1.8
 */

import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Data;

import java.util.Date;

@Data
public class Employee {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String phone;
    private String sex;
    private String idNumber;
    private Integer status;
    private Date createTime;
    private Date updateTime;
    private Long createUser;
    private Long updateUser;
}

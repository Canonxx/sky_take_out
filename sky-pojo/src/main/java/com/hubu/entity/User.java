package com.hubu.entity;/*
 * @Auther:long
 * @Date:2025/7/1
 * @Description:
 * @VERSON:1.8
 */

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;
    private String openId;
    private String name;
    private String phone;
    private String sex;
    private String idNumber;
    private String avatar;
    private Date createTime;
}

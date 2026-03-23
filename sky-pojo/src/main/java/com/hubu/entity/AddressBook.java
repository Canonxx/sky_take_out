package com.hubu.entity;/*
 * @Auther:long
 * @Date:2026/3/22
 * @Description:
 * @VERSON:1.8
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressBook implements Serializable {
    private Long id;              // 主键

    private Long userId;          // 用户id

    private String consignee;     // 收货人

    private String sex;           // 性别

    private String phone;         // 手机号

    private String provinceCode;  // 省编码

    private String provinceName;  // 省名称

    private String cityCode;      // 市编码

    private String cityName;      // 市名称

    private String districtCode;  // 区编码

    private String districtName;  // 区名称

    private String detail;        // 详细地址

    private String label;         // 标签（家/公司等）

    private Integer isDefault;    // 是否默认地址（0否 1是）
}

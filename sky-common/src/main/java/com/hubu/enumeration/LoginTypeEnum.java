package com.hubu.enumeration;/*
 * @Auther:long
 * @Date:2025/7/3
 * @Description:登录方式枚举
 * @VERSON:1.8
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginTypeEnum {
    ACCOUNT(1,"账号登录","AccountLoginStrategyImpl");
    private final Integer type;
    private final String desc;
    private final String strategy;
}

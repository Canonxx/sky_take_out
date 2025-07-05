package com.hubu.constant;/*
 * @Auther:long
 * @Date:2025/7/5
 * @Description:状态常量
 * @VERSON:1.8
 */

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.hubu.enumeration.LoginTypeEnum;

public final class LoginStatusConstant {
    //明确声明禁止实例化
    private LoginStatusConstant(){}
    // 开启或禁用
    public static final Integer enable = 1;
    public static final Integer disable = 0;
}

package com.hubu.vo;/*
 * @Auther:long
 * @Date:2026/3/8
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
public class UserLoginVO implements Serializable {
    // 用户id
    private Integer id;
    // 微信用户openid
    private String openid;
    // jwt令牌
    private String token;
}

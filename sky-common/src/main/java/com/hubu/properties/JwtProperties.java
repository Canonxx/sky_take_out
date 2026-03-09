package com.hubu.properties;/*
 * @Auther:long
 * @Date:2025/9/25
 * @Description:
 * @VERSON:1.8
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "sky.jwt")
public class JwtProperties {
    // 管理端员工jwt签名时使用的密钥相关配置
    private String adminSecretKey;
    private Long adminTtl;
    private String adminTokenName;
    // 用户端jwt令牌的相关配置信息
    private String userSecretKey;
    private Long userTtl;
    private String userTokenName;
}

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
    // jwt签名时使用的密钥
    private String adminSecretKey;
    private Long adminTtl;
    private String adminTokenName;
}

package com.hubu.controller.user;/*
 * @Auther:long
 * @Date:2026/3/8
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.constant.JwtClaimsConstant;
import com.hubu.dto.UserLoginDTO;
import com.hubu.entity.User;
import com.hubu.properties.JwtProperties;
import com.hubu.result.Result;
import com.hubu.service.user.UserService;
import com.hubu.utils.JwtUtil;
import com.hubu.vo.UserLoginVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/user")
@Slf4j
@Tag(name = "用户管理模块")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 用户微信登录接口
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO){
        log.info("获取临时登陆凭证：{}",userLoginDTO);
        // 用户wx登录，并获取用户对象
        User user = userService.login(userLoginDTO);
        // 用户信息创建token
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID,user.getId());
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);
        // 创建UserLoginVO数据对象
        UserLoginVO userLoginVO= UserLoginVO.builder()
                .id(user.getId().intValue())
                .openid(user.getOpenid())
                .token(token)
                .build();
        return Result.success(userLoginVO);
    }
}

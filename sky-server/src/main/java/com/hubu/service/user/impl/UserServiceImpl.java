package com.hubu.service.user.impl;/*
 * @Auther:long
 * @Date:2026/3/8
 * @Description:
 * @VERSON:1.8
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hubu.constant.MessageConstant;
import com.hubu.dto.UserLoginDTO;
import com.hubu.entity.User;
import com.hubu.exception.LoginFailedException;
import com.hubu.mapper.UserMapper;
import com.hubu.properties.WeChatProperties;
import com.hubu.service.user.UserService;
import com.hubu.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final String url = "https://api.weixin.qq.com/sns/jscode2session";
    @Autowired
    private WeChatProperties weChatProperties   ;
    @Autowired
    private UserMapper userMapper;
    @Override
    public User login(UserLoginDTO userLoginDTO) {
        String openId = getOpenId(userLoginDTO.getCode());
        //判断openid是否为空，为空表示登陆失败，抛出登陆异常
        if(openId==null){
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }
        // 通过openid查询用户是否存在
        User user = userMapper.selectByOpenId(openId);
        // 用户不存在，保存用户
        if(user==null){
            User user1 = User.builder()
                    .openid(openId)
                    .createTime(LocalDateTime.now())
                    .build();
            userMapper.insert(user1);
            return user1;
        }
        return user;
    }
    private String getOpenId(String code){
        // 访问微信接口获取openid
        Map map = new HashMap();
        map.put("appid",weChatProperties.getAppId() );
        map.put("secret",weChatProperties.getAppSecret());
        map.put("js_code",code);
        map.put("grant_type","authorization_code");
        String json = HttpClientUtil.doGet(url, map);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = jsonObject.getString("openid");
        return openid;
    }
}

package com.hubu.strategy.context;/*
 * @Auther:long
 * @Date:2025/7/3
 * @Description:执行登录策略
 * @VERSON:1.8
 */

import com.hubu.enumeration.LoginTypeEnum;
import com.hubu.strategy.LoginStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginStrategyContext {
    @Autowired
    private Map<String, LoginStrategy> loginStrategyMap;

    /**
     * 执行登录策略
     * @param data
     * @param loginTypeEnum
     * @return
     */
    public String executeLoginStrategy(String data, LoginTypeEnum loginTypeEnum){
        String login = loginStrategyMap.get(loginTypeEnum.getStrategy()).login(data);
        return login;
    }
}

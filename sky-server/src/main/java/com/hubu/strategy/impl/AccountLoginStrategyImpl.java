package com.hubu.strategy.impl;/*
 * @Auther:long
 * @Date:2025/7/3
 * @Description:账户登录接口实现
 * @VERSON:1.8
 */

import com.hubu.strategy.LoginStrategy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("AccountLoginStrategyImpl")
public class AccountLoginStrategyImpl implements LoginStrategy {
    /**
     * 登录功能具体实现
     * @param data
     * @return
     */
    @Override
    public String login(String data) {
        return "ok";
    }
}

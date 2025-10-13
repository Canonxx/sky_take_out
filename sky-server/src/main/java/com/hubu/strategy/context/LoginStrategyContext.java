package com.hubu.strategy.context;/*
 * @Auther:long
 * @Date:2025/7/3
 * @Description:执行登录策略
 * @VERSON:1.8
 */

import com.hubu.dto.EmployeeLoginDTO;
import com.hubu.entity.Employee;
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
     * @param employeeLoginDTO
     * @param loginTypeEnum
     * @return
     */
    public Employee executeLoginStrategy(EmployeeLoginDTO employeeLoginDTO, LoginTypeEnum loginTypeEnum){
        return loginStrategyMap.get(loginTypeEnum.getStrategy()).login(employeeLoginDTO);
    }
}

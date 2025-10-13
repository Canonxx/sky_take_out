package com.hubu.strategy;/*
 * @Auther:long
 * @Date:2025/7/3
 * @Description:登陆策略接口
 * @VERSON:1.8
 */

import com.hubu.dto.EmployeeLoginDTO;
import com.hubu.entity.Employee;

public interface LoginStrategy {
    Employee login(EmployeeLoginDTO employeeLoginDTO);
}

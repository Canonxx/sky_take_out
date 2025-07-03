package com.hubu.service.admin;/*
 * @Auther:long
 * @Date:2025/7/2
 * @Description:员工管理接口
 * @VERSON:1.8
 */

import com.hubu.entity.Employee;

import java.util.List;

public interface EmployeeService {
    /**
     *
     * @param username
     * @return
     */
    List<Employee> findEmployee(String username);
}

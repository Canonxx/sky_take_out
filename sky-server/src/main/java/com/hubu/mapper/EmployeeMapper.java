package com.hubu.mapper;/*
 * @Auther:long
 * @Date:2025/7/2
 * @Description:员工mapper层
 * @VERSON:1.8
 */

import com.hubu.entity.Employee;

import java.util.List;

public interface EmployeeMapper {
    List<Employee> findEmployee(String username);
}

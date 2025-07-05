package com.hubu.mapper;/*
 * @Auther:long
 * @Date:2025/7/2
 * @Description:员工mapper层
 * @VERSON:1.8
 */

import com.hubu.entity.Employee;

import java.util.List;

public interface EmployeeMapper {
    /**
     * 通过员工姓名查找员工
     * @param username
     * @return
     */
    Employee findEmployeeByUserName(String username);
}

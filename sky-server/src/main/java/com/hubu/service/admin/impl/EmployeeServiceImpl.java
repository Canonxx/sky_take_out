package com.hubu.service.admin.impl;/*
 * @Auther:long
 * @Date:2025/7/2
 * @Description:员工管理实现接口
 * @VERSON:1.8
 */

import com.hubu.entity.Employee;
import com.hubu.mapper.EmployeeMapper;
import com.hubu.service.admin.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private  EmployeeMapper employeeMapper;
    @Override
    public List<Employee> findEmployee(String username) {
        return employeeMapper.findEmployee(username) ;
    }
}

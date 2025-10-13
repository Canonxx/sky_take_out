package com.hubu.mapper;/*
 * @Auther:long
 * @Date:2025/7/2
 * @Description:员工mapper层
 * @VERSON:1.8
 */

import com.github.pagehelper.Page;
import com.hubu.dto.EmployeePageQueryDTO;
import com.hubu.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    /**
     * 通过员工姓名查找员工
     * @param username
     * @return
     */
    Employee findEmployeeByUserName(String username);

    void insert(Employee employee);

    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    void update(Employee employee);

    Employee findById(Long id);
}

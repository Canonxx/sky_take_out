package com.hubu.service.admin;/*
 * @Auther:long
 * @Date:2025/7/2
 * @Description:员工管理接口
 * @VERSON:1.8
 */

import com.hubu.dto.EmployeeDTO;
import com.hubu.dto.EmployeeLoginDTO;
import com.hubu.dto.EmployeePageQueryDTO;
import com.hubu.entity.Employee;
import com.hubu.vo.PageResultVO;

import java.util.List;

public interface EmployeeService {
    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    void save(EmployeeDTO employeeDTO);

    PageResultVO pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    void startOrStop(Integer status, Long id);

    Employee findById(Long id);

    void update(EmployeeDTO employeeDTO);
}

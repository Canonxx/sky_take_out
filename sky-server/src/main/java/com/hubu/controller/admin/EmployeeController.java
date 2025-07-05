package com.hubu.controller.admin;/*
 * @Auther:long
 * @Date:2025/7/2
 * @Description:员工管理
 * @VERSON:1.8
 */

import com.hubu.constant.MessageConstant;
import com.hubu.dto.EmployeeLoginDTO;
import com.hubu.entity.Employee;
import com.hubu.exception.AccountLockedException;
import com.hubu.service.admin.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("admin/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    /**
     * 员工登录功能
     * @param employeeLoginDTO 用DTO接收传来的json数据
     */
    @PostMapping("/login")
    public String login(@RequestBody EmployeeLoginDTO employeeLoginDTO){
        Employee employee = employeeService.login(employeeLoginDTO);
        return "ok";
    }
    @GetMapping("test/login")
    public void testLogin(){
        throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
    }
}

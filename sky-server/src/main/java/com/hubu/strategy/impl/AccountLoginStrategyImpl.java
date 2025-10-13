package com.hubu.strategy.impl;/*
 * @Auther:long
 * @Date:2025/7/3
 * @Description:账户登录接口实现
 * @VERSON:1.8
 */

import com.hubu.constant.LoginStatusConstant;
import com.hubu.constant.MessageConstant;
import com.hubu.dto.EmployeeLoginDTO;
import com.hubu.entity.Employee;
import com.hubu.exception.AccountLockedException;
import com.hubu.exception.AccountNotFoundException;
import com.hubu.exception.PasswordErrorException;
import com.hubu.mapper.EmployeeMapper;
import com.hubu.strategy.LoginStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Objects;

@Service("AccountLoginStrategyImpl")
public class AccountLoginStrategyImpl implements LoginStrategy {
    /**
     * 登录功能具体实现
     * @param employeeLoginDTO
     * @return
     */
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        //1. 获取员工信息
        Employee employee = employeeMapper.findEmployeeByUserName(employeeLoginDTO.getUsername());
        //2. 验证账户，密码，以及账户状态
        if (!Objects.nonNull(employee)){//账户不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        //密码错误
        if(!DigestUtils.md5DigestAsHex(employeeLoginDTO.getPassword().getBytes()).equals(employee.getPassword())){
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        //账户锁定
        if (employee.getStatus()== LoginStatusConstant.DISABLE){
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }
        return employee;
    }
}

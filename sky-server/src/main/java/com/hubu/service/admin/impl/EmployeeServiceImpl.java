package com.hubu.service.admin.impl;/*
 * @Auther:long
 * @Date:2025/7/2
 * @Description:员工管理实现接口
 * @VERSON:1.8
 */

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hubu.constant.LoginStatusConstant;
import com.hubu.constant.MessageConstant;
import com.hubu.constant.PasswordConstant;
import com.hubu.context.BaseContext;
import com.hubu.dto.EmployeeDTO;
import com.hubu.dto.EmployeeLoginDTO;
import com.hubu.dto.EmployeePageQueryDTO;
import com.hubu.entity.Employee;
import com.hubu.enumeration.LoginTypeEnum;
import com.hubu.exception.AccountLockedException;
import com.hubu.exception.AccountNotFoundException;
import com.hubu.exception.PasswordErrorException;
import com.hubu.mapper.EmployeeMapper;
import com.hubu.service.admin.EmployeeService;
import com.hubu.strategy.context.LoginStrategyContext;
import com.hubu.vo.EmployeePageQueryVO;
import com.hubu.vo.PageResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    // 登陆策略上下文
    @Autowired
    private LoginStrategyContext loginStrategyContext;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        return loginStrategyContext.executeLoginStrategy(employeeLoginDTO, LoginTypeEnum.ACCOUNT);
    }
//@Override
//public Employee login(EmployeeLoginDTO employeeLoginDTO) {
//    //1. 获取员工信息
//    Employee employee = employeeMapper.findEmployeeByUserName(employeeLoginDTO.getUsername());
//    //2. 验证账户，密码，以及账户状态
//    if (!Objects.nonNull(employee)){//账户不存在
//        throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
//    }
//    //密码错误
//    if(!DigestUtils.md5DigestAsHex(employeeLoginDTO.getPassword().getBytes()).equals(employee.getPassword())){
//        throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
//    }
//    //账户锁定
//    if (employee.getStatus()== LoginStatusConstant.DISABLE){
//        throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
//    }
//    return employee;
//}

    @Override
    public void save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        // employeeDTO属性赋值给employee
        BeanUtils.copyProperties(employeeDTO,employee);
        // 对employee其他属性赋值
        // 1.设置初始密码默认为123456
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
        // 2.设置员工状态 默认为 开启 1，关闭0
        employee.setStatus(LoginStatusConstant.ENABLE);
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        employee.setCreateUser(BaseContext.getCurrentId());
        employee.setUpdateUser(BaseContext.getCurrentId());
        employeeMapper.insert(employee);
    }

    @Override
    public PageResultVO pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("开启分页查询");
        PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());
        Page<Employee> employeePage = employeeMapper.pageQuery(employeePageQueryDTO);
        // 总记录数
        long total = employeePage.getTotal();
        // 获取当前分页数据 并转换成VO对象
        List<EmployeePageQueryVO> records = employeePage.getResult()
                .stream()
                .map(employee -> {
                    EmployeePageQueryVO employeePageQueryVO = new EmployeePageQueryVO();
                    BeanUtils.copyProperties(employee,employeePageQueryVO);
                    return employeePageQueryVO;
                })
                .collect(Collectors.toList());
        return new PageResultVO(total,records);
    }

    @Override
    public void startOrStop(Integer status, Long id) {
        Employee employee = Employee.builder()
                .status(status)
                .id(id)
                .build();
        employeeMapper.update(employee);
    }

    @Override
    public Employee findById(Long id) {
        Employee employee = employeeMapper.findById(id);
        employee.setPassword("******");
        return employee;
    }

    @Override
    public void update(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO,employee);
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(BaseContext.getCurrentId());
        employeeMapper.update(employee);
    }

}

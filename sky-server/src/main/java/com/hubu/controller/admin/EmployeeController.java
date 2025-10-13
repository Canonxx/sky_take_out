package com.hubu.controller.admin;/*
 * @Auther:long
 * @Date:2025/7/2
 * @Description:员工管理
 * @VERSON:1.8
 */

import com.hubu.constant.JwtClaimsConstant;
import com.hubu.dto.EmployeeDTO;
import com.hubu.dto.EmployeeLoginDTO;
import com.hubu.dto.EmployeePageQueryDTO;
import com.hubu.entity.Employee;
import com.hubu.properties.JwtProperties;
import com.hubu.result.Result;
import com.hubu.service.admin.EmployeeService;
import com.hubu.utils.JwtUtil;
import com.hubu.vo.EmployeeLoginVO;
import com.hubu.vo.PageResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("admin/employee")
@Tag(name = "员工管理",description = "员工相关接口")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;
    /**
     * 员工登录功能
     * @param employeeLoginDTO 用DTO接收传来的json数据
     */
    @Operation(description = "员工登录",summary = "员工登录")
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO){
        Employee employee = employeeService.login(employeeLoginDTO);
        //登录成功后，生成jwt令牌
        Map<String,Object> clamis = new HashMap<>();
        clamis.put(JwtClaimsConstant.EMP_ID,employee.getId());
        String token= JwtUtil.createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), clamis);
        //包装返回数据
        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId().intValue())
                .name(employee.getName())
                .token(token)
                .userName(employee.getUsername())
                .build();
        return Result.success(employeeLoginVO);
    }
    @PostMapping
    @Operation(description = "添加员工",summary = "添加员工")
    public Result<Object> save(@RequestBody EmployeeDTO employeeDTO){
        log.info("添加员工{}",employeeDTO);
        employeeService.save(employeeDTO);
        log.info("添加完成");
        return Result.success();
    }
    @GetMapping("/page")
    @Operation(summary = "员工分页查询",description = "员工分页查询接口")
    public Result<PageResultVO> pageQuery(EmployeePageQueryDTO employeePageQueryDTO){
        log.info("接收到员工分页查询DTO:{}",employeePageQueryDTO);
        PageResultVO pageResult =  employeeService.pageQuery(employeePageQueryDTO);
        log.info("分页查询成功");
        return Result.success(pageResult);
    }
    @PostMapping("/status/{status}")
    @Operation(description = "禁用开启员工",summary = "禁用开启员工")
    public Result startOrStop(@PathVariable Integer status,@RequestParam Long id){
        log.info("status:{},id{}",status,id);
        employeeService.startOrStop(status,id);
        log.info("开启或禁用员工成功");
        return Result.success();
    }
    @GetMapping("/{id}")
    @Operation(description = "根据id查询员工",summary = "根据id查询员工")
    public Result<Employee> findById(@PathVariable Long id){
        log.info("员工id:",id);
        Employee employee = employeeService.findById(id);
        log.info("查询完成：{}",employee);
        return Result.success(employee);
    }
    @PutMapping
    @Operation(description = "更新员工信息",summary = "更新员工信息")
    public Result update(@RequestBody EmployeeDTO employeeDTO){
        log.info("传入的员工信息：{}",employeeDTO);
        employeeService.update(employeeDTO);
        log.info("更新员工信息完成");
        return Result.success();
    }
}

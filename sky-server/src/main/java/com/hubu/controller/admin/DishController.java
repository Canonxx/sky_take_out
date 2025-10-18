package com.hubu.controller.admin;/*
 * @Auther:long
 * @Date:2025/10/17
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.dto.DishDTO;
import com.hubu.dto.DishPageQueryDTO;
import com.hubu.result.Result;
import com.hubu.service.admin.DishService;
import com.hubu.vo.CategoryPageQueryVO;
import com.hubu.vo.DishVO;
import com.hubu.vo.PageResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/dish")
@Tag(name = "菜品管理",description = "菜品管理相关操作")
public class DishController {
    @Autowired
    private DishService dishService;
    @PostMapping
    @Operation(description = "新增菜品",summary = "新增菜品")
    //
    public Result<String> add(@RequestBody DishDTO dishDTO){
        log.info("新增菜品：{}",dishDTO);
        dishService.add(dishDTO);
        log.info("新增菜品完成");
        return Result.success();
    }

    @GetMapping("/page")
    @Operation(description = "菜品分页查询",summary = "菜品分页查询")
    public Result<PageResultVO> pageQuery(DishPageQueryDTO dishPageQueryDTO){
        log.info("菜品分页查询：{}",dishPageQueryDTO);
        PageResultVO pageResultVO =  dishService.pageQuery(dishPageQueryDTO);
        log.info("分页查询成功");
        return Result.success(pageResultVO);
    }

}

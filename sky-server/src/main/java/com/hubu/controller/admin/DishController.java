package com.hubu.controller.admin;/*
 * @Auther:long
 * @Date:2025/10/17
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.dto.DishDTO;
import com.hubu.dto.DishPageQueryDTO;
import com.hubu.mapper.DishMapper;
import com.hubu.result.Result;
import com.hubu.service.admin.DishService;
import com.hubu.vo.CategoryPageQueryVO;
import com.hubu.vo.DishPageQueryVO;
import com.hubu.vo.DishVO;
import com.hubu.vo.PageResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @GetMapping("/{id}")
    @Operation(description = "根据id查询菜品信息",summary = "根据id查询菜品信息")
    public Result<DishVO> query(@PathVariable Integer id){
        log.info("根据id查询菜品{}",id);
        DishVO dishVO = dishService.query(id);
        log.info("查询成功");
        return Result.success(dishVO);
    }
    @PutMapping
    @Operation(description = "修改菜品",summary = "修改菜品")
    public Result<String> update(@RequestBody DishDTO dishDTO){
        log.info("修改菜品{}",dishDTO);
        dishService.update(dishDTO);
        log.info("修改成功！");
        return Result.success();
    }
    @PostMapping("/status/{status}")
    @Operation(description = "启售或停售",summary = "启售或停售")
    public Result<String> startOrStop(@PathVariable Integer status, @RequestParam Long id){
        log.info("启售或停售菜品id{},status{}",id,status);
        dishService.startOrStop(status,id);
        log.info("启售停售功能完成");
        return Result.success();
    }
    @DeleteMapping
    @Operation(description = "删除菜品根据id",summary = "删除彩品")
    public Result<String> delete(@RequestParam List<Long> ids){
        log.info("删除菜品ids{}",ids);
        dishService.delete(ids);
        log.info("删除成功");
        return Result.success();
    }
}

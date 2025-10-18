package com.hubu.controller.admin;/*
 * @Auther:long
 * @Date:2025/10/14
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.dto.CategoryDTO;
import com.hubu.dto.CategoryPageQueryDTO;
import com.hubu.result.Result;
import com.hubu.service.admin.CategoryService;
import com.hubu.vo.CategoryPageQueryVO;
import com.hubu.vo.PageResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@Slf4j
@Tag(name = "分类管理",description = "分类管理相关接口")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping
    @Operation(description = "新增分类",summary = "新增分类")
    public Result save(@RequestBody CategoryDTO categoryDTO){
        log.info("新增分类时数据：{}",categoryDTO);
        categoryService.save(categoryDTO);
        log.info("添加分类完成");
        return Result.success();
    }

    @GetMapping("/page")
    @Operation(description = "分类分页查询",summary = "分类分页查询")
    public Result<PageResultVO> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("分类分页查询传递过来的数据：{}",categoryPageQueryDTO);
        PageResultVO pageResultVO = categoryService.pageQuery(categoryPageQueryDTO);
        log.info("分页查询成功");
        return Result.success(pageResultVO);
    }

    @PutMapping
    @Operation(description = "修改分类",summary = "修改分类")
    public Result update(@RequestBody CategoryDTO categoryDTO){
        log.info("修改分类时传递过来的数据：{}",categoryDTO);
        categoryService.update(categoryDTO);
        log.info("修改成功");
        return Result.success();
    }
    @PostMapping("/status/{status}")
    @Operation(description = "开启或禁用分类",summary = "开启禁用分类")
    public Result startOrStop(@PathVariable Integer status,@RequestParam Long id){
        log.info("status:{},id:{}",status,id);
        categoryService.startOrStop(status,id);
        log.info("禁用开启成功");
        return Result.success();
    }
    @DeleteMapping
    @Operation(description = "删除分类",summary = "删除分类")
    public Result<String> deleteById(@RequestParam Long id){
        log.info("删除分类{}",id);
        categoryService.deleteById(id);
        log.info("删除成功");
        return Result.success();
    }

    @GetMapping("/list")
    @Operation(description = "获取菜品分类",summary = "获取菜品分类")
    public Result<List<CategoryPageQueryVO>> typeList(@RequestParam Integer type){
        log.info("获取分类类型{}",type);
        List<CategoryPageQueryVO> categoryPageQueryVOList = categoryService.typeList(type);
        log.info("查询成功");
        return Result.success(categoryPageQueryVOList);
    }
}

package com.hubu.controller.user;/*
 * @Auther:long
 * @Date:2026/3/9
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.result.Result;
import com.hubu.service.admin.CategoryService;
import com.hubu.vo.CategoryPageQueryVO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品浏览菜品分类模块
 */
@RestController("userCategoryController")
@RequestMapping("user/category")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/list")
    @Operation(description = "获取菜品分类",summary = "获取菜品分类")
    public Result<List<CategoryPageQueryVO>> typeList(Long type){
        log.info("获取分类类型{}",type);
        List<CategoryPageQueryVO> categoryPageQueryVOList = categoryService.typeList(type);
        log.info("查询成功");
        return Result.success(categoryPageQueryVOList);
    }
}

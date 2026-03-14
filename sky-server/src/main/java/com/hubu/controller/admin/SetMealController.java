package com.hubu.controller.admin;/*
 * @Auther:long
 * @Date:2025/11/11
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.dto.SetMealDTO;
import com.hubu.dto.SetMealPageQueryDTO;
import com.hubu.result.Result;
import com.hubu.service.admin.SetMealService;
import com.hubu.vo.PageResultVO;
import com.hubu.vo.SetMealVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 套餐功能接口
 */
@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
public class SetMealController {
    @Autowired
    private SetMealService setMealService;
    /**
     *分页查询
     * @param setMealPageQueryDTO 套餐分页查询DTO
     * @return
     */
    @GetMapping("/page")
    public Result<PageResultVO> pageQuery(SetMealPageQueryDTO setMealPageQueryDTO){
        log.info("分页查询传递的数据为：{}",setMealPageQueryDTO);
        PageResultVO pageResultVOResult = setMealService.pageQuery(setMealPageQueryDTO);
        log.info("分页查询完成");
        return Result.success(pageResultVOResult);
    }

    /**
     * 添加套餐
     * @param setMealDTO
     * @return
     */
    @CacheEvict(value = "setmeal",key = "#setMealDTO.categoryId")
    @PostMapping()
    public Result<T> save(@RequestBody SetMealDTO setMealDTO){
        log.info("setmealdto信息为：{}",setMealDTO);
        setMealService.save(setMealDTO);
        log.info("添加套餐完成！");
        return Result.success();
    }

    /**
     * 套餐启售或禁售功能接口
     * @param status 状态 1：启售 0：禁售
     * @param id 根据套餐id来操作
     * @return
     */
    @CacheEvict(value = "setmeal",allEntries = true)
    @PostMapping("/status/{status}")
    public Result startOrstop(@PathVariable Integer status,@RequestParam Long id){
        log.info("status:{},id:{}",status,id);
        setMealService.startOrstop(status,id);
        log.info("启售禁售功能完成");
        return Result.success();
    }

    /**
     * 套餐信息查询根据id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<SetMealVO> queryById(@PathVariable Long id){
        log.info("id为：{}",id);
        SetMealVO setMealVO = setMealService.queryById(id);
        log.info("查找完成");
        return Result.success(setMealVO);
    }

    /**
     * 更新套餐信息功能接口
     * 清理套餐缓存
     * @param setMealDTO
     * @return
     */
    @CacheEvict(value = "setmeal",allEntries = true)
    @PutMapping()
    public Result update(@RequestBody SetMealDTO setMealDTO){
        log.info("更新信息为setmealdto：{}",setMealDTO);
        setMealService.update(setMealDTO);
        log.info("更新完成");
        return Result.success();
    }

    /**
     * 删除套餐接口
     * 清除套餐缓存
     * @param ids 套餐id集合
     * @return
     */
    @CacheEvict(value = "setmeal",allEntries = true)
    @DeleteMapping()
    public Result<String> deleteSetMealBactch(@RequestParam List<Long> ids){
        log.info("套餐ids:{}",ids);
        setMealService.deleteSetMealBatch(ids);
        log.info("删除成功！");
        return Result.success();
    }
}

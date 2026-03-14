package com.hubu.controller.user;/*
 * @Auther:long
 * @Date:2026/3/9
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.constant.StatusConstant;
import com.hubu.entity.SetMeal;
import com.hubu.result.Result;
import com.hubu.service.admin.SetMealService;
import com.hubu.vo.DishItemVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userSetMealController")
@RequestMapping("user/setmeal")
@Slf4j
public class SetMealController {
    @Autowired
    private SetMealService setmealService;

    /**
     * 条件查询
     * 显示套餐和缓存套餐功能实现 基于注解来实现
     * @param categoryId
     * @return
     */
    @Cacheable(value = "setmeal",key = "#categoryId")
    /**
     value是缓存名称可以有多个key，key为缓存的key 缓存里面的key为setmeal::categoryId
    */
    @GetMapping("/list")
    public Result<List<SetMeal>> list(Long categoryId) {
        SetMeal setmeal = new SetMeal();
        setmeal.setCategoryId(categoryId);
        setmeal.setStatus(StatusConstant.ENABLE);
        List<SetMeal> list = setmealService.list(setmeal);
        log.info("套餐数据从数据库获取");
        return Result.success(list);
    }
    /**
     * 根据套餐id查询包含的菜品列表
     *
     * @param id
     * @return
     */
    @GetMapping("/dish/{id}")
    public Result<List<DishItemVO>> dishList(@PathVariable("id") Long id) {
        List<DishItemVO> list = setmealService.getDishItemById(id);
        return Result.success(list);
    }
}

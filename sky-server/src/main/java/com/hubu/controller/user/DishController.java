package com.hubu.controller.user;/*
 * @Auther:long
 * @Date:2026/3/9
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.constant.StatusConstant;
import com.hubu.entity.Dish;
import com.hubu.result.Result;
import com.hubu.service.admin.DishService;
import com.hubu.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜品模块
 */
@RestController("userDishController")
@RequestMapping("user/dish")
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 查询菜品为categoryId的集合
     * @param categoryId
     * @return
     */
//    @GetMapping("/list")
//    public Result<List<Dish>> dishCategoryList(@RequestParam Long categoryId){
//        log.info("查询分类id为：{}",categoryId);
//        List<Dish> dishList =  dishService.queryCategroyList(categoryId);
//        log.info("查询完成");
//        return Result.success(dishList);
//    }

    /**
     * 菜品查看和缓存的功能实现
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    public Result<List<DishVO>> list(Long categoryId) {
        Dish dish = new Dish();
        dish.setCategoryId(categoryId);
        dish.setStatus(StatusConstant.ENABLE);//查询起售中的菜品
        // 用 key=dish_categoryid 来查询 redis缓存中的菜品数据
        // 缓存命中，则返回，否则查询数据库，并将结果保存缓存，以及返回结果
        String key = "dish_"+categoryId;//
        List<DishVO> list = (List<DishVO>) redisTemplate.opsForValue().get(key);
        if (list !=null) {
            log.info("菜品数据从缓存中取出");
            return Result.success(list);
        }
        // 查询数据库
        list = dishService.listWithFlavor(dish);
        // 菜品数据放入缓存,空数据也放入缓存防止缓存穿透
        log.info("菜品数据放入缓存中{}",list);
        redisTemplate.opsForValue().set(key,list);
        return Result.success(list);
    }
}

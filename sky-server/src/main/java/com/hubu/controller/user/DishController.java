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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userDishController")
@RequestMapping("user/dish")
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;
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
    @GetMapping("/list")
    public Result<List<DishVO>> list(Long categoryId) {
        Dish dish = new Dish();
        dish.setCategoryId(categoryId);
        dish.setStatus(StatusConstant.ENABLE);//查询起售中的菜品
        List<DishVO> list = dishService.listWithFlavor(dish);
        return Result.success(list);
    }
}

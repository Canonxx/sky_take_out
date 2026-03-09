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
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    public Result<List<SetMeal>> list(Long categoryId) {
        SetMeal setmeal = new SetMeal();
        setmeal.setCategoryId(categoryId);
        setmeal.setStatus(StatusConstant.ENABLE);
        List<SetMeal> list = setmealService.list(setmeal);
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

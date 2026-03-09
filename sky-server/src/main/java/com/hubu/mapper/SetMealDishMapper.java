package com.hubu.mapper;/*
 * @Auther:long
 * @Date:2025/10/19
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.annotation.AutoFill;
import com.hubu.entity.SetMeal;
import com.hubu.entity.SetMealDish;
import com.hubu.enumeration.AutoFillEnum;
import com.hubu.vo.DishItemVO;

import java.util.List;

public interface SetMealDishMapper {
    List<Long> querySetMealIdByIds(List<Long> ids);

    void insert(SetMealDish setMealDish1);

    List<Long> queryDishIdBySetMealId(Long id);

    List<SetMealDish> queryBySetMealId(Long id);

    void deleteBySetMealId(Long id);


}

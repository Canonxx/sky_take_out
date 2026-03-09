package com.hubu.mapper;/*
 * @Auther:long
 * @Date:2025/10/15
 * @Description:
 * @VERSON:1.8
 */

import com.github.pagehelper.Page;
import com.hubu.annotation.AutoFill;
import com.hubu.dto.SetMealPageQueryDTO;
import com.hubu.entity.SetMeal;
import com.hubu.enumeration.AutoFillEnum;
import com.hubu.vo.DishItemVO;

import java.util.List;

public interface SetMealMapper {
    Integer countByCategoryId(Long id);

    Page<SetMeal> pageQuery(SetMealPageQueryDTO setMealPageQueryDTO);
    @AutoFill(value = AutoFillEnum.INSERT)
    Long insert(SetMeal setMeal);
    @AutoFill(AutoFillEnum.UPDATE)
    void update(SetMeal setMeal);

    SetMeal queryById(Long id);

    void delete(Long id);

    List<SetMeal> list(SetMeal setmeal);

    List<DishItemVO> getDishItemBySetMealId(Long id);
}

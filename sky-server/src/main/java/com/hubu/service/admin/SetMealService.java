package com.hubu.service.admin;/*
 * @Auther:long
 * @Date:2025/11/11
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.dto.SetMealDTO;
import com.hubu.dto.SetMealPageQueryDTO;
import com.hubu.entity.SetMeal;
import com.hubu.result.Result;
import com.hubu.vo.DishItemVO;
import com.hubu.vo.PageResultVO;
import com.hubu.vo.SetMealVO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SetMealService {
    PageResultVO pageQuery(SetMealPageQueryDTO setMealPageQueryDTO);

    void save(SetMealDTO setMealDTO);

    void startOrstop(Integer status, Long id);

    SetMealVO queryById(Long id);

    void update(SetMealDTO setMealDTO);

    void deleteSetMealBatch(List<Long> ids);

    List<SetMeal> list(SetMeal setmeal);

    List<DishItemVO> getDishItemById(Long id);
}

package com.hubu.mapper;/*
 * @Auther:long
 * @Date:2025/10/19
 * @Description:
 * @VERSON:1.8
 */

import java.util.List;

public interface SetMealDishMapper {
    List<Long> querySetMealIdByIds(List<Long> ids);
}

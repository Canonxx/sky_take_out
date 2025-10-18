package com.hubu.mapper;/*
 * @Auther:long
 * @Date:2025/10/15
 * @Description:
 * @VERSON:1.8
 */

import com.github.pagehelper.Page;
import com.hubu.annotation.AutoFill;
import com.hubu.entity.Dish;
import com.hubu.enumeration.AutoFillEnum;
import com.hubu.vo.DishVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public interface DishMapper {
    Integer countByCategoryId(Long id);
    @AutoFill(AutoFillEnum.INSERT)
    void insert(Dish dish);

    Page<DishVO> pageQuery(Dish dish);
}

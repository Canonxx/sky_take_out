package com.hubu.mapper;/*
 * @Auther:long
 * @Date:2025/10/15
 * @Description:
 * @VERSON:1.8
 */

import com.github.pagehelper.Page;
import com.hubu.annotation.AutoFill;
import com.hubu.entity.Dish;
import com.hubu.entity.DishFlavor;
import com.hubu.enumeration.AutoFillEnum;
import com.hubu.vo.DishPageQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface DishMapper {
    Integer countByCategoryId(Long id);
    @AutoFill(AutoFillEnum.INSERT)
    void insert(Dish dish);

    Page<DishPageQueryVO> pageQuery(Dish dish);

    DishPageQueryVO queryById(Integer id);

    List<DishFlavor> queryFlavorsById(Integer id);
    @AutoFill(AutoFillEnum.UPDATE)
    void update(Dish dish);

    List<Dish> queryByIds(List<Long> ids);

    void deleteBatchByIds(List<Long> ids);

}

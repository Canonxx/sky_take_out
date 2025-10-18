package com.hubu.service.admin.impl;/*
 * @Auther:long
 * @Date:2025/10/17
 * @Description:
 * @VERSON:1.8
 */

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hubu.constant.StatusConstant;
import com.hubu.dto.DishDTO;
import com.hubu.dto.DishPageQueryDTO;
import com.hubu.entity.Category;
import com.hubu.entity.Dish;
import com.hubu.entity.DishFlavor;
import com.hubu.mapper.CategoryMapper;
import com.hubu.mapper.DishFlavorMapper;
import com.hubu.mapper.DishMapper;
import com.hubu.service.admin.DishService;
import com.hubu.vo.CategoryPageQueryVO;
import com.hubu.vo.DishVO;
import com.hubu.vo.PageResultVO;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    @Override
    @Transactional
    public void add(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        dish.setStatus(StatusConstant.DISABLE);
        dishMapper.insert(dish);
        if (dishDTO.getFlavors()!=null && !dishDTO.getFlavors().isEmpty()){
            Long dishId =  dish.getId();
            for (DishFlavor dishFlavor: dishDTO.getFlavors()) {
                dishFlavor.setDishId(dishId);
            }
            dishFlavorMapper.insert(dishDTO.getFlavors());
        }
    }

    @Override
    public PageResultVO pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(),dishPageQueryDTO.getPageSize());
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishPageQueryDTO,dish);
        Page<DishVO> dishVOPage = dishMapper.pageQuery(dish);
        return new PageResultVO(dishVOPage.getTotal(),dishVOPage.getResult());
    }
}

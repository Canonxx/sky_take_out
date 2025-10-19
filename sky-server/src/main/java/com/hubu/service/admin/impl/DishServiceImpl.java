package com.hubu.service.admin.impl;/*
 * @Auther:long
 * @Date:2025/10/17
 * @Description:
 * @VERSON:1.8
 */

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hubu.constant.MessageConstant;
import com.hubu.constant.StatusConstant;
import com.hubu.dto.DishDTO;
import com.hubu.dto.DishPageQueryDTO;
import com.hubu.entity.Category;
import com.hubu.entity.Dish;
import com.hubu.entity.DishFlavor;
import com.hubu.exception.DeletionNotAllowedException;
import com.hubu.mapper.CategoryMapper;
import com.hubu.mapper.DishFlavorMapper;
import com.hubu.mapper.DishMapper;
import com.hubu.mapper.SetMealDishMapper;
import com.hubu.service.admin.DishService;
import com.hubu.vo.CategoryPageQueryVO;
import com.hubu.vo.DishPageQueryVO;
import com.hubu.vo.DishVO;
import com.hubu.vo.PageResultVO;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    @Autowired
    private SetMealDishMapper setMealDishMapper;
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
        Page<DishPageQueryVO> dishPageQueryVOS = dishMapper.pageQuery(dish);
        return new PageResultVO(dishPageQueryVOS.getTotal(),dishPageQueryVOS.getResult());
    }

    @Override
    public DishVO query(Integer id) {
        DishPageQueryVO dishPageQueryVO = dishMapper.queryById(id);
        DishVO dishvo = new DishVO();
        BeanUtils.copyProperties(dishPageQueryVO,dishvo);
        dishvo.setFlavors(dishMapper.queryFlavorsById(id));
        return dishvo;
    }
    @Transactional
    @Override
    public void update(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        // 更新菜品信息
        dishMapper.update(dish);
        //更新菜品的口味 判断一下是否又添加了新的口味
        List<DishFlavor> flavors = dishDTO.getFlavors();
        List<DishFlavor> addFlavors = new ArrayList<>();
        List<DishFlavor> updateFlavors = new ArrayList<>();
        if (flavors!=null && flavors.size()>0){
            flavors.forEach(falvor ->{
                if (falvor.getDishId()==null){
                    falvor.setDishId(dish.getId());
                    addFlavors.add(falvor);
                }else {
                    updateFlavors.add(falvor);
                }
            });
        }
        if (addFlavors.size()>0){
            dishFlavorMapper.insert(addFlavors);
        }
        if (updateFlavors.size()>0){
            dishFlavorMapper.updateBatch(updateFlavors);
        }

    }

    @Override
    public void startOrStop(Integer status, Long id) {
        Dish dish = new Dish();
        dish.setStatus(status);
        dish.setId(id);
        dishMapper.update(dish);
    }

    @Override
    public void delete(List<Long> ids) {
        // 查询菜品是否停售或在售
        List<Dish> dishList = dishMapper.queryByIds(ids);
        for (Dish dish:dishList){
            if (dish.getStatus()==StatusConstant.ENABLE){
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }
        // 查询菜品是否关联套餐
        List<Long> setMealIds =  setMealDishMapper.querySetMealIdByIds(ids);
        if (setMealIds!=null && setMealIds.size()>0){
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_SETMEAL);
        }

        // 删除菜品信息以及关联的菜品口味
        dishMapper.deleteBatchByIds(ids);
        dishFlavorMapper.deleteBatchByIds(ids);
    }
}

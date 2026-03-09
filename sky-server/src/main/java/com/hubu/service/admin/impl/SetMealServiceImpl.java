package com.hubu.service.admin.impl;/*
 * @Auther:long
 * @Date:2025/11/11
 * @Description:
 * @VERSON:1.8
 */

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hubu.constant.MessageConstant;
import com.hubu.constant.StatusConstant;
import com.hubu.context.BaseContext;
import com.hubu.dto.SetMealDTO;
import com.hubu.dto.SetMealPageQueryDTO;
import com.hubu.entity.Dish;
import com.hubu.entity.SetMeal;
import com.hubu.entity.SetMealDish;
import com.hubu.exception.DeletionNotAllowedException;
import com.hubu.exception.StartNotAllowedException;
import com.hubu.mapper.CategoryMapper;
import com.hubu.mapper.DishMapper;
import com.hubu.mapper.SetMealDishMapper;
import com.hubu.mapper.SetMealMapper;
import com.hubu.result.Result;
import com.hubu.service.admin.SetMealService;
import com.hubu.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SetMealServiceImpl implements SetMealService {
    @Autowired
    private SetMealDishMapper setMealDishMapper;
    @Autowired
    private SetMealMapper setMealMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private DishMapper dishMapper;

    @Override
    public PageResultVO pageQuery(SetMealPageQueryDTO setMealPageQueryDTO) {
        // 1.开启分页查询
        log.info("开启分页查询");
        PageHelper.startPage(setMealPageQueryDTO.getPage(), setMealPageQueryDTO.getPageSize());
        // 2. 进行分页条件查询
        Page<SetMeal> setMeals = setMealMapper.pageQuery(setMealPageQueryDTO);
        // 3. 获取数据总条数，和分页数据集合
        long total = setMeals.getTotal();
        // 将查询的实体对象转换为VO对象
        List<SetMealPageQueryVO> setMealPageQueryVOS = setMeals.getResult()
                .stream()
                .map(setMeal -> {
                    SetMealPageQueryVO setMealPageQueryVO = new SetMealPageQueryVO();
                    BeanUtils.copyProperties(setMeal, setMealPageQueryVO);
                    Long categoryId = setMeal.getCategoryId();
                    String categoryName = categoryMapper.findNameById(categoryId);
                    setMealPageQueryVO.setCategoryName(categoryName);
                    return setMealPageQueryVO;
                }).collect(Collectors.toList());
        PageResultVO pageResultVO = new PageResultVO(total, setMealPageQueryVOS);
        log.info("分页查询成功");
        return pageResultVO;
    }

    @Transactional
    @Override
    public void save(SetMealDTO setMealDTO) {
        // 1. setmealDto赋值给setmeal对象和setmealdish对象
        SetMeal setMeal = new SetMeal();
        BeanUtils.copyProperties(setMealDTO, setMeal);
        // 默认为禁售状态
        // 2. 将setmeal对象很setmealdish对象写入对应的数据库
        setMeal.setStatus(StatusConstant.DISABLE);
        log.info("setmeal对象写入数据库{},创建者是{}", setMeal, BaseContext.getCurrentId());
        setMealMapper.insert(setMeal);
        List<SetMealDish> setMealDishes = setMealDTO.getSetmealDishes();
        if (setMealDishes != null && !setMealDishes.isEmpty()) {
            setMealDishes.forEach(setMealDish -> {
                SetMealDish setMealDish1 = new SetMealDish();
                BeanUtils.copyProperties(setMealDish, setMealDish1);
                setMealDish1.setSetmealId(setMeal.getId());
                log.info("setmealdish写入数据库{}", setMealDish1);
                setMealDishMapper.insert(setMealDish1);
            });
        }
    }

    @Override
    public void startOrstop(Integer status, Long id) {
        // 1. 根据id查询套餐关联的菜品是否启售
        List<Long> dishIdList = setMealDishMapper.queryDishIdBySetMealId(id);
        if (status == StatusConstant.ENABLE && dishIdList!=null && !dishIdList.isEmpty()){
            dishIdList.forEach(dishId->{
                // 根据dishId来查询dish的状态
                DishPageQueryVO dishPageQueryVO = dishMapper.queryById(dishId.intValue());
                if (dishPageQueryVO.getStatus()== StatusConstant.DISABLE) {
                    throw new StartNotAllowedException(MessageConstant.START_NOT_ALLOWED);
                }
            });
        }
        // 2. 关联的菜品启售可以进行 套餐可以进行启售 否则不能启售
        SetMeal setMeal = SetMeal.builder()
                .status(status)
                .id(id).build();
        setMealMapper.update(setMeal);
        log.info("启售仅售完成！");
    }

    @Override
    public SetMealVO queryById(Long id) {
        // 根据id查找setmeal数据和dish数据
        SetMeal setMeal = setMealMapper.queryById(id);
        List<SetMealDish> setMealDishes = setMealDishMapper.queryBySetMealId(id);
        // 将查找到的数据赋值给VO对象
        SetMealVO setMealVO = new SetMealVO();
        BeanUtils.copyProperties(setMeal,setMealVO);
        setMealVO.setSetmealDishes(setMealDishes);
        return setMealVO;
    }
    @Transactional
    @Override
    public void update(SetMealDTO setMealDTO) {
        // 1.setmealdto数据复制给setmeal对象和setmealdish对象
        SetMeal setMeal = new SetMeal();
        BeanUtils.copyProperties(setMealDTO,setMeal);
        List<SetMealDish> setmealDishes = setMealDTO.getSetmealDishes();
        setmealDishes.forEach(setMealDish -> {setMealDish.setSetmealId(setMeal.getId());});
        // 2. 将setmeal数据更新
        setMealMapper.update(setMeal);
        // 3. 把之前套餐关联的菜品删除重新添加新的菜品
        setMealDishMapper.deleteBySetMealId(setMeal.getId());
        setmealDishes.forEach(setMealDish -> {setMealDishMapper.insert(setMealDish);});
    }
    @Transactional
    @Override
    public void deleteSetMealBatch(List<Long> ids) {
        // 1. 根据ids来删除套餐以及相关的 setmealdish
        // 2. 判断套餐的状态 在售不许删除
        ids.forEach(id->{
            if (setMealMapper.queryById(id).getStatus() == StatusConstant.ENABLE) {
                throw new DeletionNotAllowedException(MessageConstant.SETMEAL_ON_SALE);
            }
            setMealMapper.delete(id);//删除套餐
            setMealDishMapper.deleteBySetMealId(id);// 删除套餐关联的菜品数据
        });
    }

    /**
     * 条件查询
     * @param setmeal
     * @return
     */
    public List<SetMeal> list(SetMeal setmeal) {
        List<SetMeal> list = setMealMapper.list(setmeal);
        return list;
    }

    @Override
    public List<DishItemVO> getDishItemById(Long id) {
        return setMealMapper.getDishItemBySetMealId(id);
    }
}

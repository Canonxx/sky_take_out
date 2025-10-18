package com.hubu.service.admin.impl;/*
 * @Auther:long
 * @Date:2025/10/14
 * @Description:
 * @VERSON:1.8
 */

import com.fasterxml.jackson.databind.ser.Serializers;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hubu.constant.CategoryStatusConstant;
import com.hubu.constant.MessageConstant;
import com.hubu.context.BaseContext;
import com.hubu.dto.CategoryDTO;
import com.hubu.dto.CategoryPageQueryDTO;
import com.hubu.entity.Category;
import com.hubu.exception.DeletionNotAllowedException;
import com.hubu.mapper.CategoryMapper;
import com.hubu.mapper.DishMapper;
import com.hubu.mapper.SetMealMapper;
import com.hubu.service.admin.CategoryService;
import com.hubu.vo.CategoryPageQueryVO;
import com.hubu.vo.PageResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetMealMapper setMealMapper;
    @Override
    public void save(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        // 设置分类状态 默认为开启
        category.setStatus(CategoryStatusConstant.ENABLE);
//        category.setCreateTime(LocalDateTime.now());
//        category.setUpdateTime(LocalDateTime.now());
//        category.setCreateUser(BaseContext.getCurrentId());
//        category.setUpdateUser(BaseContext.getCurrentId());
        categoryMapper.insert(category);
    }

    @Override
    public PageResultVO pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        // 开启分页查询
        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
        // 生成一个category对象，将DTO对象赋值给category对象对应的属性
        Category category = new Category();
        BeanUtils.copyProperties(categoryPageQueryDTO,category);
        Page<Category> page = categoryMapper.find(category);
        long total = page.getTotal();
        List<CategoryPageQueryVO> records = page.getResult()
                .stream()
                .map(category1 -> {
                    CategoryPageQueryVO categoryPageQueryVO = new CategoryPageQueryVO();
                    BeanUtils.copyProperties(category1,categoryPageQueryVO);
                    return categoryPageQueryVO;
                })
                .collect(Collectors.toList());
        return new PageResultVO(total,records);
    }

    @Override
    public void update(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        category.setUpdateUser(BaseContext.getCurrentId());
        categoryMapper.update(category);
    }

    @Override
    public void startOrStop(Integer status, Long id) {
        Category category = Category.builder()
                .id(id)
                .status(status)
                .updateUser(BaseContext.getCurrentId())
                .build();
        categoryMapper.update(category);
    }

    @Override
    public void deleteById(Long id) {
        Integer count = dishMapper.countByCategoryId(id);
        if (count>0){
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_DISH);
        }
        categoryMapper.deleteById(id);
        count = setMealMapper.countByCategoryId(id);
        if(count>0){
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_SETMEAL);
        }
        categoryMapper.deleteById(id);
    }

    @Override
    public List<CategoryPageQueryVO> typeList(Integer type) {
        Category category = new Category();
        category.setType(type);
        log.info("开始查询");
        List<Category> categoryList =  categoryMapper.find(category);
        log.info("查询结束");
        List<CategoryPageQueryVO> categoryPageQueryVOList = categoryList.stream()
                .map(category1 -> {
                    CategoryPageQueryVO categoryPageQueryVO = new CategoryPageQueryVO();
                    BeanUtils.copyProperties(category1, categoryPageQueryVO);
                    return categoryPageQueryVO;
                })
                .collect(Collectors.toList());
        return categoryPageQueryVOList;
    }
}

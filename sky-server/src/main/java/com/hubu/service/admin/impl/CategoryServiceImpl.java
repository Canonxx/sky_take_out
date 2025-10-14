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
import com.hubu.context.BaseContext;
import com.hubu.dto.CategoryDTO;
import com.hubu.dto.CategoryPageQueryDTO;
import com.hubu.entity.Category;
import com.hubu.mapper.CategoryMapper;
import com.hubu.service.admin.CategoryService;
import com.hubu.vo.CategoryPageQueryVO;
import com.hubu.vo.PageResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public void save(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        // 设置分类状态 默认为开启
        category.setStatus(CategoryStatusConstant.ENABLE);
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateUser(BaseContext.getCurrentId());
        category.setUpdateUser(BaseContext.getCurrentId());
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
}

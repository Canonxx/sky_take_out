package com.hubu.mapper;/*
 * @Auther:long
 * @Date:2025/10/14
 * @Description:
 * @VERSON:1.8
 */

import com.github.pagehelper.Page;
import com.hubu.entity.Category;

public interface CategoryMapper {
    void insert(Category category);

    Page<Category> find(Category category);

    void update(Category category);

    void deleteById(Long id);
}

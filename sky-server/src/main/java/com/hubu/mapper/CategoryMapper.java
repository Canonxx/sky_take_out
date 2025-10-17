package com.hubu.mapper;/*
 * @Auther:long
 * @Date:2025/10/14
 * @Description:
 * @VERSON:1.8
 */

import com.github.pagehelper.Page;
import com.hubu.annotation.AutoFill;
import com.hubu.entity.Category;
import com.hubu.enumeration.AutoFillEnum;

public interface CategoryMapper {
    @AutoFill(AutoFillEnum.INSERT)
    void insert(Category category);

    Page<Category> find(Category category);
    @AutoFill(AutoFillEnum.UPDATE)
    void update(Category category);

    void deleteById(Long id);
}

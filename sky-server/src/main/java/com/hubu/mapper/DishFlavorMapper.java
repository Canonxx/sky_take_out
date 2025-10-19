package com.hubu.mapper;/*
 * @Auther:long
 * @Date:2025/10/18
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.entity.DishFlavor;

import java.util.List;

public interface DishFlavorMapper {
    void insert(List<DishFlavor> flavors);

    void updateBatch(List<DishFlavor> flavors);

    void deleteBatchByIds(List<Long> ids);
}

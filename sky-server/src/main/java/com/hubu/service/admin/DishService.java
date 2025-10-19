package com.hubu.service.admin;/*
 * @Auther:long
 * @Date:2025/10/17
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.dto.DishDTO;
import com.hubu.dto.DishPageQueryDTO;
import com.hubu.vo.CategoryPageQueryVO;
import com.hubu.vo.DishPageQueryVO;
import com.hubu.vo.DishVO;
import com.hubu.vo.PageResultVO;

import java.util.List;

public interface DishService {
    void add(DishDTO dishDTO);

    PageResultVO pageQuery(DishPageQueryDTO dishPageQueryDTO);

    DishVO query(Integer id);

    void update(DishDTO dishDTO);

    void startOrStop(Integer status, Long id);

    void delete(List<Long> ids);
}

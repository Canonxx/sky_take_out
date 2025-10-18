package com.hubu.service.admin;/*
 * @Auther:long
 * @Date:2025/10/14
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.dto.CategoryDTO;
import com.hubu.dto.CategoryPageQueryDTO;
import com.hubu.vo.CategoryPageQueryVO;
import com.hubu.vo.PageResultVO;

import java.util.List;

public interface CategoryService {
    void save(CategoryDTO categoryDTO);

    PageResultVO pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    void update(CategoryDTO categoryDTO);

    void startOrStop(Integer status, Long id);

    void deleteById(Long id);

    List<CategoryPageQueryVO> typeList(Integer type);
}

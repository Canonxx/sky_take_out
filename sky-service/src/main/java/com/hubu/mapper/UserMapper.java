package com.hubu.mapper;/*
 * @Auther:long
 * @Date:2025/7/1
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.entity.User;

import java.util.List;

public interface UserMapper {
    List<User> findAll();
}

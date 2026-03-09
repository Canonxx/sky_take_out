package com.hubu.mapper;/*
 * @Auther:long
 * @Date:2025/7/1
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper {

    User selectByOpenId(String openId);

    void insert(User user1);
}

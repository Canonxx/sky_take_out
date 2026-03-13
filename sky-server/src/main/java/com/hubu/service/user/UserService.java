package com.hubu.service.user;/*
 * @Auther:long
 * @Date:2026/3/8
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.dto.UserLoginDTO;
import com.hubu.entity.User;
import com.hubu.vo.UserLoginVO;

public interface UserService {
    User login(UserLoginDTO userLoginDTO);
}

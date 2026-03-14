package com.hubu.service.user;/*
 * @Auther:long
 * @Date:2026/3/14
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.dto.ShoppingCartDTO;
import com.hubu.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    void add(ShoppingCartDTO shoppingCartDTO);

    List<ShoppingCart> list();

    void sub(ShoppingCartDTO shoppingCartDTO);

    void delete();
}

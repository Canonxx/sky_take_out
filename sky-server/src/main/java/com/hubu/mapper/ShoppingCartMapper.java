package com.hubu.mapper;/*
 * @Auther:long
 * @Date:2026/3/14
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartMapper {
    /**
     * 查询购物车信息
     * @param shoppingcart
     * @return
     */
    List<ShoppingCart> query(ShoppingCart shoppingcart);

    /**
     * 更新购物车信息
     * @param shoppingcart
     */
    void update(ShoppingCart shoppingcart);

    /**
     * 添加商品至购物车
     * @param shoppingcart
     */
    void insert(ShoppingCart shoppingcart);

    /**
     * 删除商品从购物车
     * @param shoppingCart
     */
    void delete(ShoppingCart shoppingCart);
}

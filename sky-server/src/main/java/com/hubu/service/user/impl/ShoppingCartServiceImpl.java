package com.hubu.service.user.impl;/*
 * @Auther:long
 * @Date:2026/3/14
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.context.BaseContext;
import com.hubu.dto.ShoppingCartDTO;
import com.hubu.entity.SetMeal;
import com.hubu.entity.ShoppingCart;
import com.hubu.mapper.DishMapper;
import com.hubu.mapper.SetMealMapper;
import com.hubu.mapper.ShoppingCartMapper;
import com.hubu.service.user.ShoppingCartService;
import com.hubu.vo.DishPageQueryVO;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanIsAbstractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetMealMapper setMealMapper;

    /**
     * 添加商品至购物车
     * @param shoppingCartDTO
     */
    @Override
    public void add(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingcart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO,shoppingcart);
        shoppingcart.setUserId(BaseContext.getCurrentId());
        List<ShoppingCart> shoppingCart1 = shoppingCartMapper.query(shoppingcart);
        // 1.分析 dto数据添加的是菜品，套餐，还是带口味的菜品
        // 添加菜品到购物车
        // 判断菜品是否在购物车里
        if (shoppingCart1!=null){
            // 在购物车里,菜品数量+1
            shoppingcart.setNumber(shoppingCart1.get(0).getNumber()+1);
            shoppingcart.setCreateTime(LocalDateTime.now());
            // 更新购物车里的菜品信息
            shoppingCartMapper.update(shoppingcart);
        } else {
            if (shoppingCartDTO.getDishId()!=null) {
                // 不在购物车里面，添加菜品信息至购物车
                DishPageQueryVO dishPageQueryVO = dishMapper.queryById(shoppingCartDTO.getDishId().intValue());
                shoppingcart.setName(dishPageQueryVO.getName());
                shoppingcart.setImage(dishPageQueryVO.getImage());
                shoppingcart.setNumber(1);
                shoppingcart.setAmount(dishPageQueryVO.getPrice());
                shoppingcart.setCreateTime(LocalDateTime.now());
                shoppingCartMapper.insert(shoppingcart);
            }else {
                // 添加套餐信息至购物车
                SetMeal setMeal = setMealMapper.queryById(shoppingCartDTO.getSetmealId());
                shoppingcart.setName(setMeal.getName());
                shoppingcart.setImage(setMeal.getImage());
                shoppingcart.setNumber(1);
                shoppingcart.setAmount(setMeal.getPrice());
                shoppingcart.setCreateTime(LocalDateTime.now());
                shoppingCartMapper.insert(shoppingcart);
            }
        }


    }

    /**
     * 查看购物车
     * @return
     */
    @Override
    public List<ShoppingCart> list() {
        Long userId = BaseContext.getCurrentId();
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserId(userId);
        List<ShoppingCart> shoppingCartList = shoppingCartMapper.query(shoppingCart);
        return shoppingCartList;
    }

    /**
     * 删除购物车里面的商品
     * @param shoppingCartDTO
     */
    @Override
    public void sub(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO,shoppingCart);
        shoppingCart.setUserId(BaseContext.getCurrentId());
        List<ShoppingCart> query = shoppingCartMapper.query(shoppingCart);
        shoppingCart.setNumber(query.get(0).getNumber()-1);
        shoppingCart.setCreateTime(LocalDateTime.now());
        shoppingCartMapper.update(shoppingCart);
    }

    /**
     * 清空购物车
     */
    @Override
    public void delete() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserId(BaseContext.getCurrentId());
        shoppingCartMapper.delete(shoppingCart);
    }
}

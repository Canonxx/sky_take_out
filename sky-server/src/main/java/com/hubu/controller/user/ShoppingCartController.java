package com.hubu.controller.user;/*
 * @Auther:long
 * @Date:2026/3/14
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.dto.ShoppingCartDTO;
import com.hubu.entity.ShoppingCart;
import com.hubu.result.Result;
import com.hubu.service.user.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/shoppingCart")
@Slf4j
@Tag(name = "购物车业务接口")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    /**
     * 添加购物车
     * 每次请求只能添加套餐或菜品
     * @param shoppingCartDTO
     * @return
     */
    @PostMapping("/add")
    @Operation(description = "添加购物车")
    public Result add(@RequestBody ShoppingCartDTO shoppingCartDTO){
        log.info("添加购物车的商品为：{}",shoppingCartDTO);
        shoppingCartService.add(shoppingCartDTO);
        log.info("完成商品添加到购物车！");
        return Result.success();
    }

    /**
     * 查看购物车商品
     * @return
     */
    @GetMapping("/list")
    @Operation(description = "查看购物车信息")
    public Result<List<ShoppingCart>> list(){
        List<ShoppingCart> list = shoppingCartService.list();
        return Result.success(list);
    }
    @PostMapping("/sub")
    @Operation(description = "从购物车删除商品")
    public Result sub(@RequestBody ShoppingCartDTO shoppingCartDTO){
        log.info("删除购物车的商品为：{}",shoppingCartDTO);
        shoppingCartService.sub(shoppingCartDTO);
        log.info("完成购物车商品删除！");
        return Result.success();
    }

    /**
     * 清空购物车
     * @return
     */
    @DeleteMapping("clean")
    @Operation(description = "清空购物车")
    public Result delete(){
        shoppingCartService.delete();
        return Result.success();
    }
}

package com.hubu.constant;/*
 * @Auther:long
 * @Date:2025/7/5
 * @Description:信息常量
 * @VERSON:1.8
 */

public final class MessageConstant {


    public static final String SHOPPING_CART_IS_NULL = "购物车为空";

    private MessageConstant(){}
    public static final String ACCOUNT_NOT_FOUND = "账户不存在";
    public static final String PASSWORD_ERROR = "密码不存在";
    public static final String ACCOUNT_LOCKED = "账户锁定";
    public static final String CATEGORY_BE_RELATED_BY_DISH = "当前分类关联了菜品，不能删除";
    public static final String CATEGORY_BE_RELATED_BY_SETMEAL = "当前分类关联了套餐，不能删除";
    public static final String UPLOAD_FAILED = "文件上传失败";
    public static final String DISH_ON_SALE = "菜品在售，不能删除";
    public static final String DISH_BE_RELATED_SETMEAL = "菜品关联套餐，不能删除";
    public static final String TOKEN_EXPIRED = "token过期，重新登录";
    public static final String SESSION_INVALID = "session无效，重新登录";
    public static final String START_NOT_ALLOWED = "不允许启售，其关联的菜品为禁售";
    public static final String SETMEAL_ON_SALE = "套餐在售，不能删除";
    public static final String LOGIN_FAILED = "登陆异常";
    public static final String ADDRESS_BOOK_IS_NULL = "地址簿为空";
}

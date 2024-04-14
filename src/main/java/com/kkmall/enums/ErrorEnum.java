package com.kkmall.enums;

import lombok.Getter;

@Getter
public enum ErrorEnum {
    ERROR(-1, "服务器错误"),

    USER_EXIST(-2, "用户已存在"),

    LOGIN_FAIL(-3, "登陆失败"),

    USERNAME_OR_PASSWORD_ERROR(-4, "用户名或者密码错误"),

    USERNAME_EXIT(-5, "用户名已存在"),

    EMAIL_EXIT(-6, "邮箱已存在"),

    LOGIN_AGAIN(-7, "登录失效,请重新登录！"),

    EMAIL_ERROR(-8, "邮箱不匹配"),

    COMMODITY_NOT_EXIST(-9, "商品不存在"),

    ALREADY_COLLECTION(-10, "商品已经收藏"),

    NOT_COLLECTION(-11, "商品未收藏"),

    COMMODITY_ADDED(-12, "商品已添加,请勿重复添加！"),

    CART_NOT_EXIST(-13, "该购物车不存在！"),

    ADDRESS_EXIST(-14, "地址已存在！"),

    ADDRESS_NOT_EXIST(-15, "地址不存在！"),

    COMMODITY_ID_ERROR(-16, "商品id不存在！"),

    UPDATE_COMMODITY_INFORMATION_FAIL(-17, "更新商品信息失败！"),

    INVENTORY_NOT_ENOUGH(-18, "库存不足！"),


    /**
     * 订单
     */
    ORDER_PAY(1, "订单已经支付！"),

    ORDER_NOT_PAY(2, "订单未支付！"),

    ORDER_NOT_EXIST(-1, "订单不存在！"),

    CART_NOT_HAVE_COMMODITY(-2, "购物车中没有商品！"),

    CANT_CANCEL_ORDER(-3, "订单未支付或已经发货！"),

    ORDER_NOT_ARRIVE_OR_OBTAIN(-4, "订单未到达或已经收货！"),

    ORDER_CANT_DELETE(-5, "订单正在派送或未付款！"),

    ;

    private final Integer code;

    private final String desc;

    ErrorEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}

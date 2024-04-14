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

    ;

    private final Integer code;

    private final String desc;

    ErrorEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}

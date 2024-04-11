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

    ;

    private final Integer code;

    private final String desc;

    ErrorEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}

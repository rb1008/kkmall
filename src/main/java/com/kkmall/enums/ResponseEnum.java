package com.kkmall.enums;


public enum ResponseEnum {
    NEED_LOGIN(0, "未登录，请先登录"),
    SUCCESS(1, "成功"),


    ;

    final Integer code;
    final String desc;

    ResponseEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}

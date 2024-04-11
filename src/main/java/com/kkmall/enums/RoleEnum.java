package com.kkmall.enums;

public enum RoleEnum {
    MANAGER(1),

    USER(0),

    ;

    final Integer code;

    RoleEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}

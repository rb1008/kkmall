package com.kkmall.enums;

public enum OrderEnum {
    CANCEL(-1),

    PAY(0),

    NOT_SENT(1),

    SENT(2),

    IN_WAY(3),

    ARRIVE(4),

    OBTAIN(5),

    ;

    private final Integer code;

    OrderEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}

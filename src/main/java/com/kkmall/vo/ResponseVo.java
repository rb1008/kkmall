package com.kkmall.vo;

import com.kkmall.enums.ResponseEnum;

public class ResponseVo<T> {
    Integer code;

    String msg;

    T data;

    public ResponseVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseVo(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResponseVo<T> error(Integer code, String msg) {
        return new ResponseVo<>(code, msg);
    }

    public static <T> ResponseVo<T> error(Integer code, String msg, T data) {
        return new ResponseVo<>(code, msg, data);
    }

    public static <T> ResponseVo<T> success(Integer code, String msg, T data) {
        return new ResponseVo<>(code, msg, data);
    }

    public static <T> ResponseVo<T> success(T data) {
        return new ResponseVo<>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getDesc(), data);
    }
}

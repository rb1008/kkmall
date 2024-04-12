package com.kkmall.vo;

import com.kkmall.enums.ResponseEnum;
import lombok.Data;

@Data
public class ResponseVo<T> {
    private Integer code;

    private String msg;

    private T data;

    private String url;

    public ResponseVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseVo(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseVo(Integer code, String msg, T data, String url) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.url = url;
    }

    public static <T> ResponseVo<T> error(Integer code, String msg) {
        return new ResponseVo<>(code, msg);
    }

    public static <T> ResponseVo<T> error(Integer code, String msg, T data, String url) {
        return new ResponseVo<>(code, msg, data, url);
    }

    public static <T> ResponseVo<T> success(Integer code, String msg, T data) {
        return new ResponseVo<>(code, msg, data);
    }

    public static <T> ResponseVo<T> success(Integer code, String msg, T data, String url) {
        return new ResponseVo<>(code, msg, data, url);
    }

    public static <T> ResponseVo<T> success(T data, String url) {
        return new ResponseVo<>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getDesc(), data, url);
    }

    public static <T> ResponseVo<T> success(T data) {
        return new ResponseVo<>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getDesc(), data);
    }
}

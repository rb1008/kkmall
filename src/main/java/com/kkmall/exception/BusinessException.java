package com.kkmall.exception;

import com.kkmall.enums.ErrorEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@NoArgsConstructor
@Slf4j
public class BusinessException extends RuntimeException{

    private ErrorEnum errorEnum;

    public BusinessException(ErrorEnum errorEnum){
        log.error(errorEnum.getDesc());
        this.errorEnum = errorEnum;
    }
}

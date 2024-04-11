package com.kkmall.exception;

import com.kkmall.enums.ErrorEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BusinessException extends RuntimeException{

    private ErrorEnum errorEnum;

    public BusinessException(ErrorEnum errorEnum){
        this.errorEnum = errorEnum;
    }
}

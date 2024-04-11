package com.kkmall.exception;

import com.kkmall.enums.ErrorEnum;
import com.kkmall.enums.ResponseEnum;
import com.kkmall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseVo<ErrorEnum> loginExceptionHandler(BusinessException e) {
        e.printStackTrace();
        ErrorEnum errorEnum = e.getErrorEnum();
        return ResponseVo.error(errorEnum.getCode(), errorEnum.getDesc());
    }

    @ExceptionHandler(Exception.class)
    public ResponseVo<ErrorEnum> exception(Exception e){
        e.printStackTrace();
        ErrorEnum errorEnum = ErrorEnum.ERROR;
        return ResponseVo.error(errorEnum.getCode(), errorEnum.getDesc());
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    public ResponseVo<ErrorEnum> exception(BadSqlGrammarException e){
        e.printStackTrace();
        ErrorEnum errorEnum = ErrorEnum.ERROR;
        return ResponseVo.error(errorEnum.getCode(), errorEnum.getDesc());
    }
}

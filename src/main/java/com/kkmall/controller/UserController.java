package com.kkmall.controller;

import com.kkmall.entity.User;
import com.kkmall.enums.ErrorEnum;
import com.kkmall.form.UserForm;
import com.kkmall.service.UserService;
import com.kkmall.vo.ResponseVo;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public ResponseVo<User> register(@Valid @RequestBody UserForm form, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseVo.error(ErrorEnum.ERROR.getCode(),
                    Objects.requireNonNull(result.getFieldError()).getField() + result.getFieldError().getDefaultMessage());
        }
        return ResponseVo.success(userService.register(form));
    }
}

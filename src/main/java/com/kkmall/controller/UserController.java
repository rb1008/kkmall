package com.kkmall.controller;

import com.kkmall.entity.User;
import com.kkmall.enums.ErrorEnum;
import com.kkmall.enums.ResponseEnum;
import com.kkmall.form.UserForm;
import com.kkmall.service.UserService;
import com.kkmall.vo.ResponseVo;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@RestController
@RequestMapping
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public ResponseVo<User> register(@Valid @RequestBody UserForm form, BindingResult result) throws NoSuchAlgorithmException {
        if (result.hasErrors()) {
            return ResponseVo.error(ErrorEnum.ERROR.getCode(),
                    Objects.requireNonNull(result.getFieldError()).getField() + result.getFieldError().getDefaultMessage());
        }
        return ResponseVo.success(userService.register(form));
    }

    @PostMapping("/login")
    public ResponseVo<User> login(String email, String password, HttpServletResponse response) throws NoSuchAlgorithmException {
        return ResponseVo.success(userService.login(email, password, response));
    }

    @GetMapping("/logout")
    public ResponseVo<ResponseEnum> logout(@RequestHeader String token, HttpServletResponse response) {
        return ResponseVo.success(userService.logout(token, response));
    }
}

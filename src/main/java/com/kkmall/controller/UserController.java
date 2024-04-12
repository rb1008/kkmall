package com.kkmall.controller;

import com.alibaba.fastjson2.JSONObject;
import com.kkmall.entity.User;
import com.kkmall.enums.ErrorEnum;
import com.kkmall.enums.ResponseEnum;
import com.kkmall.form.UserForm;
import com.kkmall.form.UserInfoForm;
import com.kkmall.service.UserService;
import com.kkmall.vo.ResponseVo;
import com.kkmall.vo.UserVo;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
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

    @PostMapping("/uploadAvatar")
    public ResponseVo<Object> uploadAvatar(@RequestHeader String token, @RequestParam("avatar") MultipartFile avatar) {
        return ResponseVo.success(JSONObject.from(userService.uploadAvatar(token, avatar)));
    }

    @PostMapping("/updateProfile")
    public ResponseVo<UserVo> updateProfile(@RequestHeader String token, @Valid @RequestBody UserInfoForm form) {
        return ResponseVo.success(userService.updateProfile(token, form));
    }

    @PostMapping("/updatePassword")
    public ResponseVo<Object> updatePassword(@RequestHeader String token, String newPassword, String oldPassword) throws NoSuchAlgorithmException {
        return ResponseVo.success(JSONObject.from(userService.updatePassword(token, newPassword, oldPassword)));
    }

    @PostMapping("/forgetPassword")
    public ResponseVo<Object> forgetPassword(@RequestHeader String token, String email, String password) throws NoSuchAlgorithmException {
        return ResponseVo.success(JSONObject.from(userService.forgetPassword(token, email, password)));
    }

    @PostMapping("/changeRole")
    public ResponseVo<Map<String, Integer>> changeRole(@RequestHeader String token, Integer role) {
        return ResponseVo.success(userService.updateUserRole(token, role));
    }
}

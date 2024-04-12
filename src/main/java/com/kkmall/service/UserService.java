package com.kkmall.service;

import com.kkmall.entity.User;
import com.kkmall.enums.ResponseEnum;
import com.kkmall.form.UserForm;

import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

public interface UserService {
    User register(UserForm form) throws NoSuchAlgorithmException;

    User login(String email, String password, HttpServletResponse response) throws NoSuchAlgorithmException;

    ResponseEnum logout(String token, HttpServletResponse response);
}

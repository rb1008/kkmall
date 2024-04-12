package com.kkmall.service;

import com.kkmall.entity.User;
import com.kkmall.enums.ResponseEnum;
import com.kkmall.form.UserForm;
import com.kkmall.form.UserInfoForm;
import com.kkmall.vo.UserVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public interface UserService {
    User register(UserForm form) throws NoSuchAlgorithmException;

    User login(String email, String password, HttpServletResponse response) throws NoSuchAlgorithmException;

    ResponseEnum logout(String token, HttpServletResponse response);

    Object uploadAvatar(String token, MultipartFile avatar);

    UserVo updateProfile(String token, UserInfoForm form);

    Object updatePassword(String token, String newPassword, String oldPassword) throws NoSuchAlgorithmException;

    Object forgetPassword(String token,String email,String password) throws NoSuchAlgorithmException;

    Map<String,Integer> updateUserRole(String token,Integer role);
}

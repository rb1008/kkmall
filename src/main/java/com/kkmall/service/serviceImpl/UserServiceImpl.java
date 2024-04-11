package com.kkmall.service.serviceImpl;

import com.kkmall.dao.UserMapper;
import com.kkmall.entity.User;
import com.kkmall.enums.ErrorEnum;
import com.kkmall.exception.BusinessException;
import com.kkmall.form.UserForm;
import com.kkmall.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User register(UserForm userForm) {
        User user = new User();
        BeanUtils.copyProperties(userForm,user);
        int userNameCount = userMapper.selectByUsername(user.getUsername());
        if (userNameCount != 0) {
            throw new BusinessException(ErrorEnum.USERNAME_EXIT);
        }
        int emailCount = userMapper.selectByEmail(user.getEmail());
        if (emailCount != 0) {
            throw new BusinessException(ErrorEnum.EMAIL_EXIT);
        }

        user.setPassword(user.getPassword());
        int count = userMapper.insert(user);
        if (count == 0) {
            throw new BusinessException(ErrorEnum.ERROR);
        }
        userDetail(user);
        return user;
    }

    private void userDetail(User user){
        user.setPassword("");
        user.setRole(0);
        user.setImg("https://su-share.oss-cn-beijing.aliyuncs.com/2/483f32b2d8364ec690024c3cb8fc754a.jpg");
    }
}

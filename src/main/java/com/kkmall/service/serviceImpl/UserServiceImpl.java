package com.kkmall.service.serviceImpl;

import com.kkmall.dao.UserMapper;
import com.kkmall.entity.User;
import com.kkmall.enums.ErrorEnum;
import com.kkmall.enums.ResponseEnum;
import com.kkmall.exception.BusinessException;
import com.kkmall.form.UserForm;
import com.kkmall.service.UserService;
import com.kkmall.utils.JwtUtil;
import com.kkmall.utils.PasswordUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

import static com.kkmall.consts.kkMallConst.TOKEN;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User register(UserForm userForm) throws NoSuchAlgorithmException {
        User user = new User();
        BeanUtils.copyProperties(userForm, user);
        int userNameCount = userMapper.selectByUsername(user.getUsername());
        if (userNameCount > 0) {
            throw new BusinessException(ErrorEnum.USERNAME_EXIT);
        }

        int emailCount = userMapper.selectByEmail(user.getEmail());
        if (emailCount > 0) {
            throw new BusinessException(ErrorEnum.EMAIL_EXIT);
        }

        user.setPassword(PasswordUtil.encode(user.getPassword()));
        user.setRole(0);
        user.setImg("https://su-share.oss-cn-beijing.aliyuncs.com/2/483f32b2d8364ec690024c3cb8fc754a.jpg");
        int count = userMapper.insert(user);
        if (count == 0) {
            throw new BusinessException(ErrorEnum.ERROR);
        }
        user.setPassword("");
        return user;
    }


    @Override
    public User login(String email, String password, HttpServletResponse response) throws NoSuchAlgorithmException {
        User user = userMapper.selectUserByEmail(email);
        boolean contain = PasswordUtil.contain(user.getPassword(), password);
        if (!contain) {
            throw new BusinessException(ErrorEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        String token = JwtUtil.createToken(String.valueOf(user.getId()), String.valueOf(user.getRole()));
        response.setHeader(TOKEN, token);
        user.setPassword("");
        return user;
    }

    @Override
    public ResponseEnum logout(String token, HttpServletResponse response) {
        if(token == null||token.isEmpty()||JwtUtil.isExpiration(token)){
            throw new BusinessException(ErrorEnum.ERROR);
        }
        response.setHeader(TOKEN,null);
        return ResponseEnum.SUCCESS;
    }
}

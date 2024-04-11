package com.kkmall.exception;

import com.kkmall.entity.User;
import com.kkmall.enums.ErrorEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.kkmall.consts.kkMallConst.CURRENT_USER;

@Configuration
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute(CURRENT_USER);
        if(user == null){
            throw new BusinessException(ErrorEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        return true;
    }
}

package com.kkmall.service;

import com.kkmall.entity.User;
import com.kkmall.form.UserForm;

public interface UserService {
    User register(UserForm form);
}

package com.kkmall.form;

import lombok.Data;
import reactor.util.annotation.Nullable;

import javax.validation.constraints.NotNull;

@Data
public class UserInfoForm {
    @NotNull
    private String username;

    @NotNull
    private String email;

    @Nullable
    private String info;
}

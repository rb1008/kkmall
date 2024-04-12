package com.kkmall.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String img;

    private String info;

    private Date registerTime = new Date();

    private Integer role;

    private Date updateTime = new Date();
}
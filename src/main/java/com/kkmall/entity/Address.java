package com.kkmall.entity;

import lombok.Data;

@Data
public class Address {
    private Integer id;

    private Integer userId;

    private String name;

    private String address;

    private String phone;

    private Integer isDefault;
}
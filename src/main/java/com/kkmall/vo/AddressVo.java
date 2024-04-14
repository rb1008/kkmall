package com.kkmall.vo;

import lombok.Data;

@Data
public class AddressVo {
    private Integer userId;

    private String name;

    private String address;

    private String phone;

    private Integer isDefault;
}

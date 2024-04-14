package com.kkmall.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Cart {
    private Integer id;

    private Integer userId;

    private Integer commodityId;

    private Integer count;

    private Date addTime = new Date();
}
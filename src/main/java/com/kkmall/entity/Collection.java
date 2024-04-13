package com.kkmall.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Collection {
    private Integer id;

    private Integer userId;

    private Integer commodityId;

    private Date addTime = new Date();
}
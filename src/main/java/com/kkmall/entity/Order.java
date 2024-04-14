package com.kkmall.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private Integer id;

    private String orderNumber;

    private Integer userId;

    private Integer commodityId;

    private String courierNumber;

    private String courierName;

    private Date createTime = new Date();

    private Date payTime = new Date();

    private Date sippingTime;

    private Date successTime;

    private Integer status;

    private Integer addressId;

    private String content;
}
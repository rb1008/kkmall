package com.kkmall.vo;

import lombok.Data;

import java.util.Date;

@Data
public class OrderVo {

    private String orderNumber;

    private Integer userId;

    private Integer commodityId;

    private String courierNumber;

    private String courierName;

    private Date createTime;

    private Date payTime;

    private Date sippingTime;

    private Date successTime;

    private Integer status;

    private String content;
}

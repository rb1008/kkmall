package com.kkmall.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Commodity {
    private Integer id;

    private String name;

    private String color;

    private String material;

    private String origin;

    private Integer classifyId;

    private BigDecimal originalPrice;

    private BigDecimal nowPrice;

    private Integer inventory;

    private Date publishTime;

    private Integer status;

    private String img;

    private Integer saleCount;
}
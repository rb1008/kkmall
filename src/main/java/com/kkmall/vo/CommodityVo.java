package com.kkmall.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CommodityVo {

    private String name;

    private String info;

    private String description;

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
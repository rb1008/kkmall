package com.kkmall.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CartVo {
    private Integer id;

    private Integer commodityId;

    private Integer count;

    private Date addTime;
}

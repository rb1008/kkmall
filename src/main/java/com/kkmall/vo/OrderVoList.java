package com.kkmall.vo;

import lombok.Data;

import java.util.List;

@Data
public class OrderVoList {
    private List<OrderVo> orderVo;

    private AddressVo addressVo;

}

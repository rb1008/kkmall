package com.kkmall.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@NotNull
@Data
public class OrderForm {
    private String orderNumber;
    private String courierNumber;
    private String courierName;
}

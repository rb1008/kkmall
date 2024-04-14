package com.kkmall.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@NotNull
public class AddressForm {
    private String name;

    private String address;

    private String phone;

    private Integer isDefault;
}

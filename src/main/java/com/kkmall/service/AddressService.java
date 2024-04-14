package com.kkmall.service;

import com.kkmall.entity.Address;
import com.kkmall.form.AddressForm;

public interface AddressService {
    Address addAddress(String token, AddressForm form);

    Object deleteAddress(String token, Integer id);

    Address updateAddress(String token,Address address);
}

package com.kkmall.service;

import com.kkmall.entity.Address;
import com.kkmall.form.AddressForm;

public interface AddressService {
    Address addAddress(String token, AddressForm form);
}

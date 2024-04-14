package com.kkmall.service;

import com.kkmall.entity.Cart;

public interface CartService {
    Object addCart(String token, Integer commodityId, Integer count);

    Object deleteCart(String token, Integer id);

    Cart changeCartCount(String token, Integer id, Integer count);
}

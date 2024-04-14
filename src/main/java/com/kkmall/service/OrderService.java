package com.kkmall.service;

import com.kkmall.form.OrderForm;
import com.kkmall.vo.OrderVoList;

public interface OrderService {

    OrderVoList buyCommodity(String token, Integer addressId);

    Object deliverGoods(String token, OrderForm form);

    Object cancelOrder(String token, String orderNumber);

    Object confirmOrder(String token, String orderNumber);

    Object deleteOrder(String token,String orderNumber);
}

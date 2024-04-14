package com.kkmall.controller;

import com.alibaba.fastjson2.JSONObject;
import com.kkmall.form.OrderForm;
import com.kkmall.service.OrderService;
import com.kkmall.vo.OrderVoList;
import com.kkmall.vo.ResponseVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/buyCommodity")
    public ResponseVo<OrderVoList> buyCommodity(@RequestHeader String token,
                                                @RequestParam("addressId") Integer addressId) {
        return ResponseVo.success(orderService.buyCommodity(token, addressId));
    }

    @PostMapping("/deliverGoods")
    public ResponseVo<Object> deliverGoods(@RequestHeader String token,
                                           @Valid @RequestBody OrderForm form) {
        return ResponseVo.success(JSONObject.from(orderService.deliverGoods(token, form)));
    }

    @PostMapping("/cancelOrder")
    public ResponseVo<Object> cancelOrder(@RequestHeader String token, @RequestParam("orderNumber") String orderNumber) {
        return ResponseVo.success(JSONObject.from(orderService.cancelOrder(token, orderNumber)));
    }

    @PostMapping("/confirmOrder")
    public ResponseVo<Object> confirmOrder(@RequestHeader String token,
                                           @RequestParam String orderNumber) {
        return ResponseVo.success(JSONObject.from(orderService.confirmOrder(token, orderNumber)));
    }

    @PostMapping("/deleteOrder")
    public ResponseVo<Object> deleteOrder(@RequestHeader String token,
                                          @RequestParam String orderNumber) {
        return ResponseVo.success(JSONObject.from(orderService.deleteOrder(token, orderNumber)));
    }
}

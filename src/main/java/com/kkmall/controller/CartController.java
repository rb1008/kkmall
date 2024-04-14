package com.kkmall.controller;

import com.alibaba.fastjson2.JSONObject;
import com.kkmall.service.CartService;
import com.kkmall.vo.CartVo;
import com.kkmall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping
public class CartController {

    @Resource
    private CartService cartService;

    @PostMapping("/addCart")
    public ResponseVo<Object> addCart(@RequestHeader String token,
                                      @RequestParam("commodityId") Integer commodityId,
                                      @RequestParam("count") Integer count) {
        return ResponseVo.success(JSONObject.from(cartService.addCart(token, commodityId, count)));
    }

    @PostMapping("/deleteCart")
    public ResponseVo<Object> deleteCart(@RequestHeader String token,
                                         @RequestParam("id") Integer id) {
        return ResponseVo.success(JSONObject.from(cartService.deleteCart(token, id)));
    }

    @PostMapping("/changeCartCount")
    public ResponseVo<CartVo> changeCartCount(@RequestHeader String token,
                                              @RequestParam("id") Integer id,
                                              @RequestParam("count") Integer count) {
        CartVo cartVo = new CartVo();
        BeanUtils.copyProperties(cartService.changeCartCount(token, id, count), cartVo);
        return ResponseVo.success(cartVo);
    }
}

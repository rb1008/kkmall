package com.kkmall.controller;

import com.alibaba.fastjson2.JSONObject;
import com.kkmall.entity.Address;
import com.kkmall.form.AddressForm;
import com.kkmall.service.AddressService;
import com.kkmall.vo.AddressVo;
import com.kkmall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping
public class AddressController {

    @Resource
    private AddressService addressService;

    @PostMapping("/addAddress")
    public ResponseVo<AddressVo> addAddress(@RequestHeader String token,
                                            @Valid @RequestBody AddressForm form) {
        AddressVo addressVo = new AddressVo();
        BeanUtils.copyProperties(addressService.addAddress(token, form), addressVo);
        return ResponseVo.success(addressVo);
    }

    @PostMapping("/deleteAddress")
    public ResponseVo<Object> deleteAddress(@RequestHeader String token,
                                            @RequestParam("id") Integer id) {
        return ResponseVo.success(JSONObject.from(addressService.deleteAddress(token, id)));
    }

    @PostMapping("/updateAddress")
    public ResponseVo<AddressVo> updateAddress(@RequestHeader String token,
                                               @RequestBody Address address) {
        AddressVo addressVo = new AddressVo();
        BeanUtils.copyProperties(addressService.updateAddress(token, address), addressVo);
        return ResponseVo.success(addressVo);
    }
}

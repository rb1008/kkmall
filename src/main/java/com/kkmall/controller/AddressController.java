package com.kkmall.controller;

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
}

package com.kkmall.controller;

import com.alibaba.fastjson2.JSONObject;
import com.kkmall.form.CommodityForm;
import com.kkmall.service.CommodityService;
import com.kkmall.vo.CommodityVo;
import com.kkmall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping
public class CommodityController {
    @Resource
    private CommodityService commodityService;

    @PostMapping("/uploadGoodsImg")
    public ResponseVo<Object> uploadGoodsImg(@RequestHeader String token, Integer id, @RequestParam("imgFile") MultipartFile imgFile) {
        return ResponseVo.success(JSONObject.from(commodityService.uploadGoodsImg(token, id, imgFile)));
    }

    @PostMapping("/publishNewGoods")
    public ResponseVo<CommodityVo> publishNewGoods(@RequestHeader String token, @Valid @RequestBody CommodityForm form) {
        CommodityVo commodityVo = new CommodityVo();
        BeanUtils.copyProperties(commodityService.publishNewGoods(token, form), commodityVo);
        return ResponseVo.success(commodityVo);
    }

    @PostMapping("/editGoods")
    public ResponseVo<CommodityVo> editGoods(@RequestHeader String token, @Valid @RequestBody CommodityForm form) {
        CommodityVo commodityVo = new CommodityVo();
        BeanUtils.copyProperties(commodityService.editGoods(token, form), commodityVo);
        return ResponseVo.success(commodityVo);
    }

    @PostMapping("/changeStatus")
    public ResponseVo<CommodityVo> changeStatus(@RequestHeader String token, Integer id, Integer status) {
        CommodityVo commodityVo = new CommodityVo();
        BeanUtils.copyProperties(commodityService.changeStatus(token, id, status), commodityVo);
        return ResponseVo.success(commodityVo);
    }
}

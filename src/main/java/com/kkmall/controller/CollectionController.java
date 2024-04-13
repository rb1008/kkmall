package com.kkmall.controller;

import com.kkmall.enums.ResponseEnum;
import com.kkmall.service.CollectionService;
import com.kkmall.vo.ResponseVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping
public class CollectionController {

    @Resource
    private CollectionService collectionService;

    @PostMapping("/doCollect")
    public ResponseVo<ResponseEnum> doCollect(@RequestHeader String token, @RequestParam("id") Integer id) {
        return ResponseVo.success(collectionService.doCollect(token, id));
    }
}

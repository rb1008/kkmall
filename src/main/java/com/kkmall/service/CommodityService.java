package com.kkmall.service;

import com.kkmall.entity.Commodity;
import com.kkmall.form.CommodityForm;
import org.springframework.web.multipart.MultipartFile;

public interface CommodityService {
    Object uploadGoodsImg(String token, Integer id, MultipartFile file);

    Commodity publishNewGoods(String token, CommodityForm form);

    Commodity editGoods(String token, CommodityForm form);

    Commodity changeStatus(String token, Integer id, Integer status);
}

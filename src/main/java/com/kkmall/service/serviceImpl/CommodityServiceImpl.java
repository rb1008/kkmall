package com.kkmall.service.serviceImpl;

import com.kkmall.dao.CommodityMapper;
import com.kkmall.entity.Commodity;
import com.kkmall.enums.ErrorEnum;
import com.kkmall.exception.BusinessException;
import com.kkmall.form.CommodityForm;
import com.kkmall.service.CommodityService;
import com.kkmall.utils.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Resource
    private CommodityMapper commodityMapper;

    @Override
    public Object uploadGoodsImg(String token, Integer id, MultipartFile file) {
        if (JwtUtil.isExpiration(token)) {
            throw new BusinessException(ErrorEnum.LOGIN_AGAIN);
        }
        String url = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Commodity commodity = commodityMapper.selectByCommodityId(id);
        if (commodity == null) {
            throw new BusinessException(ErrorEnum.ERROR);
        }
        commodity.setImg(url);
        int count = commodityMapper.updateByPrimaryKey(commodity);
        if (count == 0) {
            throw new BusinessException(ErrorEnum.ERROR);
        }
        return new Object();
    }

    @Override
    public Commodity publishNewGoods(String token, CommodityForm form) {
        if (JwtUtil.isExpiration(token)) {
            throw new BusinessException(ErrorEnum.LOGIN_AGAIN);
        }
        int count = commodityMapper.selectByCommodityName(form.getName());
        if (count != 0) {
            throw new BusinessException(ErrorEnum.COMMODITY_ADDED);
        }
        Commodity commodity = new Commodity();
        BeanUtils.copyProperties(form, commodity);
        int insertCommodity = commodityMapper.insertCommodity(commodity);
        if (insertCommodity == 0) {
            throw new BusinessException(ErrorEnum.ERROR);
        }
        return commodity;
    }

    @Override
    public Commodity editGoods(String token, CommodityForm form) {
        if (JwtUtil.isExpiration(token)) {
            throw new BusinessException(ErrorEnum.LOGIN_AGAIN);
        }
        Commodity commodity = commodityMapper.selectByCommodityId(form.getId());
        if (commodity == null) {
            throw new BusinessException(ErrorEnum.COMMODITY_NOT_EXIST);
        }
        BeanUtils.copyProperties(form, commodity);
        int count = commodityMapper.updateByPrimaryKey(commodity);
        if (count == 0) {
            throw new BusinessException(ErrorEnum.ERROR);
        }
        return commodity;
    }

    @Override
    public Commodity changeStatus(String token, Integer id, Integer status) {
        if (JwtUtil.isExpiration(token)) {
            throw new BusinessException(ErrorEnum.LOGIN_AGAIN);
        }
        Commodity commodity = commodityMapper.selectByCommodityId(id);
        if (commodity == null) {
            throw new BusinessException(ErrorEnum.COMMODITY_NOT_EXIST);
        }
        commodity.setStatus(status);
        int count = commodityMapper.updateByPrimaryKey(commodity);
        if (count == 0) {
            throw new BusinessException(ErrorEnum.ERROR);
        }
        return commodity;
    }
}

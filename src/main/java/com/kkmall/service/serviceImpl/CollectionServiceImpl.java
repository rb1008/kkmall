package com.kkmall.service.serviceImpl;

import com.kkmall.dao.CollectionMapper;
import com.kkmall.dao.CommodityMapper;
import com.kkmall.entity.Collection;
import com.kkmall.entity.Commodity;
import com.kkmall.enums.ErrorEnum;
import com.kkmall.enums.ResponseEnum;
import com.kkmall.exception.BusinessException;
import com.kkmall.service.CollectionService;
import com.kkmall.utils.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class CollectionServiceImpl implements CollectionService {

    @Resource
    private CollectionMapper collectionMapper;

    @Resource
    private CommodityMapper commodityMapper;

    @Override
    public ResponseEnum doCollect(String token, Integer id) {
        if (JwtUtil.isExpiration(token)) {
            throw new BusinessException(ErrorEnum.LOGIN_AGAIN);
        }
        Commodity commodity = commodityMapper.selectByCommodityId(id);
        if (commodity == null) {
            throw new BusinessException(ErrorEnum.COMMODITY_NOT_EXIST);
        }
        Integer uid = Integer.parseInt(JwtUtil.getUserId(token));
        boolean contain = collectionMapper.selectByUidAndCommodityId(uid, commodity.getId());
        if (contain) {
            throw new BusinessException(ErrorEnum.ALREADY_COLLECTION);
        }
        Collection collection = new Collection();
        collection.setUserId(uid);
        collection.setCommodityId(commodity.getId());
        int count = collectionMapper.insertSelective(collection);
        if (count == 0) {
            throw new BusinessException(ErrorEnum.ERROR);
        }
        return ResponseEnum.SUCCESS;
    }

    @Override
    public ResponseEnum undoCollect(String token, Integer id) {
        if (JwtUtil.isExpiration(token)) {
            throw new BusinessException(ErrorEnum.LOGIN_AGAIN);
        }
        int uid = Integer.parseInt(JwtUtil.getUserId(token));
        int count = collectionMapper.deleteCollectionByUidAndCommodityId(uid, id);
        if(count == 0){
            throw new BusinessException(ErrorEnum.NOT_COLLECTION);
        }
        return ResponseEnum.SUCCESS;
    }
}

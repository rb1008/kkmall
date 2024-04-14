package com.kkmall.service.serviceImpl;

import com.kkmall.dao.CartMapper;
import com.kkmall.entity.Cart;
import com.kkmall.enums.ErrorEnum;
import com.kkmall.exception.BusinessException;
import com.kkmall.service.CartService;
import com.kkmall.utils.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Resource
    private CartMapper cartMapper;

    @Override
    public Object addCart(String token, Integer commodityId, Integer count) {
        if (JwtUtil.isExpiration(token)) {
            throw new BusinessException(ErrorEnum.LOGIN_AGAIN);
        }
        int uid = Integer.parseInt(JwtUtil.getUserId(token));
        Cart cart = cartMapper.selectCartByUidAndCommodityId(uid, commodityId);
        if (cart == null) {
            Cart addCart = new Cart();
            addCart.setCount(count);
            addCart.setCommodityId(commodityId);
            addCart.setUserId(uid);
            int num = cartMapper.insert(addCart);
            if (num == 0) {
                throw new BusinessException(ErrorEnum.ERROR);
            }
        } else {
            cart.setAddTime(new Date());
            cart.setCount(count);
            int update = cartMapper.updateByPrimaryKey(cart);
            if (update == 0) {
                throw new BusinessException(ErrorEnum.ERROR);
            }
        }
        return new Object();
    }

    @Override
    public Object deleteCart(String token, Integer id) {
        if (JwtUtil.isExpiration(token)) {
            throw new BusinessException(ErrorEnum.LOGIN_AGAIN);
        }
        Cart cart = cartMapper.selectByPrimaryKey(id);
        if (cart == null) {
            throw new BusinessException(ErrorEnum.CART_NOT_EXIST);
        }
        int count = cartMapper.deleteByPrimaryKey(cart.getId());
        if (count == 0) {
            throw new BusinessException(ErrorEnum.ERROR);
        }
        return new Object();
    }

    @Override
    public Cart changeCartCount(String token, Integer id, Integer count) {
        if (JwtUtil.isExpiration(token)) {
            throw new BusinessException(ErrorEnum.LOGIN_AGAIN);
        }
        Cart cart = cartMapper.selectByPrimaryKey(id);
        if (cart == null) {
            throw new BusinessException(ErrorEnum.CART_NOT_EXIST);
        }
        cart.setCount(count);
        int update = cartMapper.updateByPrimaryKeySelective(cart);
        if (update == 0) {
            throw new BusinessException(ErrorEnum.ERROR);
        }
        return cart;
    }
}

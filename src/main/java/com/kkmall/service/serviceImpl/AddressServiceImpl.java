package com.kkmall.service.serviceImpl;

import com.kkmall.dao.AddressMapper;
import com.kkmall.entity.Address;
import com.kkmall.enums.ErrorEnum;
import com.kkmall.exception.BusinessException;
import com.kkmall.form.AddressForm;
import com.kkmall.service.AddressService;
import com.kkmall.utils.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressMapper addressMapper;

    @Override
    public Address addAddress(String token, AddressForm form) {
        if (JwtUtil.isExpiration(token)) {
            throw new BusinessException(ErrorEnum.LOGIN_AGAIN);
        }
        Address address = new Address();
        BeanUtils.copyProperties(form, address);
        address.setUserId(Integer.parseInt(JwtUtil.getUserId(token)));
        int exist = addressMapper.selectByAddress(address);
        if (exist != 0) {
            throw new BusinessException(ErrorEnum.ADDRESS_EXIST);
        }
        int count = addressMapper.insertSelective(address);
        if (count == 0) {
            throw new BusinessException(ErrorEnum.ERROR);
        }
        return address;
    }

    @Override
    public Object deleteAddress(String token, Integer id) {
        if (JwtUtil.isExpiration(token)) {
            throw new BusinessException(ErrorEnum.LOGIN_AGAIN);
        }
        int count = addressMapper.deleteByPrimaryKey(id);
        if (count == 0) {
            throw new BusinessException(ErrorEnum.ADDRESS_NOT_EXIST);
        }
        return new Object();
    }

    @Override
    public Address updateAddress(String token, Address address) {
        if (JwtUtil.isExpiration(token)) {
            throw new BusinessException(ErrorEnum.LOGIN_AGAIN);
        }
        int update = addressMapper.updateByPrimaryKeySelective(address);
        if (update == 0) {
            throw new BusinessException(ErrorEnum.ADDRESS_NOT_EXIST);
        }
        return address;
    }
}

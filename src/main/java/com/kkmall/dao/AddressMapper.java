package com.kkmall.dao;

import com.kkmall.entity.Address;
import org.apache.ibatis.annotations.Select;

public interface AddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    @Select("SELECT COUNT(1) FROM address " +
            "WHERE user_id = #{userId} AND name = #{name} AND address = #{address} AND phone = #{phone}")
    int selectByAddress(Address address);
}
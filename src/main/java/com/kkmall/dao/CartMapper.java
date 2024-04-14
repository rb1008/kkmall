package com.kkmall.dao;

import com.kkmall.entity.Cart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    @Select("SELECT * FROM cart WHERE user_id = #{uid} AND commodity_id = #{commodityId}")
    Cart selectCartByUidAndCommodityId(Integer uid, Integer commodityId);

    @Select("SELECT * FROM cart WHERE user_id = #{id}")
    List<Cart> selectCommodityIdByUid(Integer id);

    @Delete("DELETE FROM cart WHERE user_id = #{uid}")
    int deleteByUid(Integer uid);
}
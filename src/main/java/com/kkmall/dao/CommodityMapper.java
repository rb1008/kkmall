package com.kkmall.dao;

import com.kkmall.entity.Commodity;
import org.apache.ibatis.annotations.Select;

public interface CommodityMapper {
    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKey(Commodity record);

    @Select("SELECT * FROM commodity WHERE id = #{id}")
    Commodity selectByCommodityId(Integer id);
}
package com.kkmall.dao;

import com.kkmall.entity.Commodity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommodityMapper {
    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKey(Commodity record);

    @Select("SELECT * FROM commodity WHERE id = #{id}")
    Commodity selectByCommodityId(Integer id);

    @Select("SELECT COUNT(1) FROM commodity WHERE name = #{commodityName}")
    int selectByCommodityName(String commodityName);

    @Insert("INSERT INTO " +
            "commodity" +
            "(name,info,description,color,material,origin,classify_id,original_price," +
            "now_price,inventory,publish_time,status,img,sale_count) " +
            "VALUES (#{name}, #{info}, #{description}, #{color}, #{material}, #{origin}," +
            " #{classifyId}, #{originalPrice}, #{nowPrice}, #{inventory}, #{publishTime}, #{status}, #{img}, #{saleCount})")
    int insertCommodity(Commodity record);

    List<Commodity> selectCommodityByCommodityIdList(@Param("commodityIdList") List<Integer> commodityIdList);
}
package com.kkmall.dao;

import com.kkmall.entity.Collection;
import org.apache.ibatis.annotations.Select;

public interface CollectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Collection record);

    int insertSelective(Collection record);

    Collection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collection record);

    int updateByPrimaryKey(Collection record);

    @Select("SELECT EXISTS(SELECT COUNT(1) FROM collection WHERE user_id = #{uid} AND commodity_id = #{commodityId}) as result")
    boolean selectByUidAndCommodityId(Integer uid,Integer commodityId);
}
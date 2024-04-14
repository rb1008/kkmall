package com.kkmall.dao;

import com.kkmall.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKeyWithBLOBs(Order record);

    int updateByPrimaryKey(Order record);

    boolean insertOrderByList(@Param("orderList") List<Order> orderList);

    @Select("SELECT * FROM `order` WHERE order_number = #{orderNum}")
    Order selectByOrderNum(String orderNum);
}
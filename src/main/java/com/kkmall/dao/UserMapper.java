package com.kkmall.dao;

import com.kkmall.entity.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    @Select("SELECT COUNT(1) FROM user WHERE username = #{name}")
    int selectByUsername(String name);

    @Select("SELECT COUNT(1) FROM user WHERE email = #{email}")
    int selectByEmail(String email);

    @Select("SELECT * FROM user WHERE email = #{email}")
    User selectUserByEmail(String email);


}
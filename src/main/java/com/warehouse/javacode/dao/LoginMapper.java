package com.warehouse.javacode.dao;

import org.apache.ibatis.annotations.Param;

import com.warehouse.javacode.domain.Login;

public interface LoginMapper {
    int deleteByPrimaryKey(String id);

    int insert(Login record);

    int insertSelective(Login record);

    Login selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Login record);

    int updateByPrimaryKey(Login record);
    
    Login selectByNameAndPass(@Param("username") String username,@Param("password") String password);
}
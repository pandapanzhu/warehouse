package com.warehouse.javacode.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.warehouse.javacode.domain.Stuff;

public interface StuffMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(Stuff record);

    Stuff selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Stuff record);
    
    List<Object> getStuffListBySearch(@Param("search")String search,@Param("num") int num,@Param("size") int size);

	int getStuffCountBySearch(@Param("search")String search);

	List<Stuff> getAllStuffList();//得到所有的员工信息，包括离职和未离职的

	List<Stuff> getNormalStuffList();

}
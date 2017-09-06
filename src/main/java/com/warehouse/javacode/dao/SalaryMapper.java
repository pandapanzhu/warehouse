package com.warehouse.javacode.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.warehouse.javacode.domain.Salary;
import com.warehouse.javacode.domain.extend.StuffSalary;

public interface SalaryMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(Salary record);

    Salary selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Salary record);

	List<Object> getAllStuffSalaryBySearch(@Param("search")String search, @Param("year")int year, @Param("month")int month, @Param("offset")int offSet, @Param("pageSize")int pageSize);

	int getSalaryCountBySearch(@Param("search")String search, @Param("year")int year, @Param("month")int month);
	
	Salary checkSalaryByIYM(@Param("stuffid")String stuffid,@Param("year") Integer year, @Param("month")Integer month);

	StuffSalary getSalaryDetailById(String id);

	List<Salary> getSalaryByYearAndMonth(@Param("year")int year, @Param("month")int month);

}
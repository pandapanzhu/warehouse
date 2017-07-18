package com.warehouse.javacode.dao;

import java.util.List;

import com.warehouse.javacode.domain.Salary;

public interface SalaryMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(Salary record);

    Salary selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Salary record);
    
    List<Salary> getAllStuffSalary();

}
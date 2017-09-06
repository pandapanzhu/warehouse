package com.warehouse.javacode.dao;

import java.util.List;

import com.warehouse.javacode.domain.Salaryminus;
import com.warehouse.javacode.domain.Salaryplus;

public interface SalaryminusMapper {
	
	Salaryminus selectByPrimaryKey(String minusid);
	
	int deleteByPrimaryKey(String minusid);

    int insertSelective(Salaryminus record);
    
    int updateByPrimaryKeySelective(Salaryplus record);
    
    List<Salaryminus> getSalaryMinusBySalaryId(String id);

	int deleteMinusById(String minusid);
}
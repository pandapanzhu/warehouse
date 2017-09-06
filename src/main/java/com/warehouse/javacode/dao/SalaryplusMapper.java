package com.warehouse.javacode.dao;

import java.util.List;

import com.warehouse.javacode.domain.Salaryplus;

public interface SalaryplusMapper {
    int deleteByPrimaryKey(String plusid);

    int insertSelective(Salaryplus record);

    Salaryplus selectByPrimaryKey(String plusid);

    int updateByPrimaryKeySelective(Salaryplus record);

    List<Salaryplus> getSalaryPlusBySalaryId(String id);
    
    int deletePlusById(String plusid);
    


}
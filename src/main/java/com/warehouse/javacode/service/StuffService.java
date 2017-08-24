package com.warehouse.javacode.service;

import java.util.List;

import com.warehouse.javacode.domain.Salary;
import com.warehouse.javacode.domain.Stuff;
import com.warehouse.javacode.util.PageUtil;

public interface StuffService {
	PageUtil getStuffList(String search, int pageNum, int pageSize);
	int saveOrUpdateStuff(Stuff stuff);
	Stuff getStuffById(String id);
	int stuffleaveByStatus(String id, int status);//离职报道处理事件
	int dltStuffById(String id);//删除操作，软删除
	
	List<Stuff> getAllStuff();
	
	PageUtil getStuffSalaryList(int pageNum, int pageSize, String search, int year,int month);
	
	List<Stuff> getStatusNormalStuff();
	
	//每个月增加员工的工资信息
	void createSalaryByMonth(List<Salary> salaries);
}

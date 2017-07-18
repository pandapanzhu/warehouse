package com.warehouse.javacode.service;

import java.util.List;

import com.warehouse.javacode.domain.Salary;
import com.warehouse.javacode.domain.Stuff;
import com.warehouse.javacode.util.PageUtil;

public interface StuffService {
	List<Salary> getAllStuffSalary(String search);
	PageUtil getStuffList(String search, int pageNum, int pageSize);
	int saveOrUpdateStuff(Stuff stuff);
	Stuff getStuffById(String id);
	int stuffleaveByStatus();//离职报道处理事件
}

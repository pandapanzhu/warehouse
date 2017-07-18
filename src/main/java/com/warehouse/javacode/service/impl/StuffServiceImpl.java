package com.warehouse.javacode.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.warehouse.javacode.dao.SalaryMapper;
import com.warehouse.javacode.dao.StuffMapper;
import com.warehouse.javacode.domain.Salary;
import com.warehouse.javacode.domain.Stuff;
import com.warehouse.javacode.service.StuffService;
import com.warehouse.javacode.util.PageUtil;
import com.warehouse.javacode.util.UUIDUtil;

@Service
public class StuffServiceImpl implements StuffService{

	@Resource
	private SalaryMapper salaryMapper;
	
	@Resource
	private StuffMapper stuffMapper;
	
	@Override
	public List<Salary> getAllStuffSalary(String seaech) {
		return salaryMapper.getAllStuffSalary();
	}
	@Override
	public PageUtil getStuffList(String search,int pageNum,int pageSize) {
		int allRow=stuffMapper.getStuffCountBySearch(search);//总条数
		int offset = PageUtil.countOffset(pageSize, pageNum); //当前页开始记录
		List<Object> stuffList= stuffMapper.getStuffListBySearch(search,offset,pageSize);
		PageUtil pageUtil=new PageUtil(pageNum, pageSize, allRow);
		pageUtil.setList(stuffList);
		return pageUtil;
	}
	
	@Override
	public int saveOrUpdateStuff(Stuff stuff) {
		int i=0;
		if(StringUtils.isNotBlank(stuff.getId())){//如果Id不为空，则证明其是修改，否则就为添加
			i=stuffMapper.updateByPrimaryKeySelective(stuff);
		}else{
			stuff.setId(UUIDUtil.getUUID());
			i=stuffMapper.insertSelective(stuff);
		}
		return i;
	}
	
	
	@Override
	public Stuff getStuffById(String id) {
		Stuff stuff=stuffMapper.selectByPrimaryKey(id);
		return stuff;
	}
	@Override
	public int stuffleaveByStatus() {
		// TODO Auto-generated method stub
		return 0;
	}

	
}

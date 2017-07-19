package com.warehouse.javacode.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.warehouse.javacode.dao.SalaryMapper;
import com.warehouse.javacode.dao.StuffMapper;
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
	
	/**
	 * 薪水，按月计算。
	 */

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
	public int stuffleaveByStatus(String id,int status) {
		//根据Id查找对应的信息
		Stuff stuff=stuffMapper.selectByPrimaryKey(id);
		if(stuff==null){//没有找到该信息
			return 0;
		}else{
			stuff.setStatus(status);
			//更新了信息之后，再进行保存
			int i=stuffMapper.updateByPrimaryKeySelective(stuff);
			return i;
		}
		
	}
	@Override
	public int dltStuffById(String id) {
		Stuff stuff=stuffMapper.selectByPrimaryKey(id);
		if(stuff==null){//没有找到该信息
			return 0;
		}else{
			stuff.setDlt(1);
			//更新了信息之后，再进行保存
			int i=stuffMapper.updateByPrimaryKeySelective(stuff);
			return i;
		}
	}
	@Override
	public List<Stuff> getAllStuff() {
		return stuffMapper.getAllStuffList();
	}
	@Override
	public PageUtil getStuffSalaryList(int pageNum, int pageSize, String search, String date) {
		int year=1;
		int month=1;
		int allRow=salaryMapper.getSalaryCountBySearch(search,year,month);//总条数
		int offset = PageUtil.countOffset(pageSize, pageNum); //当前页开始记录
		List<Object> stuffList= salaryMapper.getAllStuffSalaryBySearch(search, year, month, offset, pageSize);
		PageUtil pageUtil=new PageUtil(pageNum, pageSize, allRow);
		pageUtil.setList(stuffList);
		return pageUtil;
	}

	
}

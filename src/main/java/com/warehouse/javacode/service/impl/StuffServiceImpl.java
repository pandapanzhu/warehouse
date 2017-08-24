package com.warehouse.javacode.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.warehouse.javacode.dao.SalaryMapper;
import com.warehouse.javacode.dao.StuffMapper;
import com.warehouse.javacode.domain.Salary;
import com.warehouse.javacode.domain.Stuff;
import com.warehouse.javacode.service.StuffService;
import com.warehouse.javacode.util.PageUtil;
import com.warehouse.javacode.util.StampUtil;
import com.warehouse.javacode.util.UUIDUtil;

@Service
public class StuffServiceImpl implements StuffService{

	final static Logger logger=Logger.getLogger(StuffServiceImpl.class);
	
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
	public PageUtil getStuffSalaryList(int pageNum, int pageSize, String search,int year,int month) {
		
		int allRow=salaryMapper.getSalaryCountBySearch(search,year,month);//总条数
		int offset = PageUtil.countOffset(pageSize, pageNum); //当前页开始记录
		List<Object> stuffList= salaryMapper.getAllStuffSalaryBySearch(search, year, month, offset, pageSize);
		Gson gson=new Gson();
		String jsonString=gson.toJson(stuffList);//将list转换为json对象
		System.out.println(jsonString);
//		Gson json1=gson.fromJson(jsonString, getClass());
//		for(Object salary:stuffList){
//			
////			Gson gson=new Gson();
////			JsonArray jsonArray=gson.
//		}
		
		PageUtil pageUtil=new PageUtil(pageNum, pageSize, allRow);
		pageUtil.setList(stuffList);
		return pageUtil;
	}

	@Override
	public List<Stuff> getStatusNormalStuff() {
		List<Stuff> getNormalStuffs=stuffMapper.getNormalStuffList();
		return getNormalStuffs;
	}

	@Override
	public void createSalaryByMonth(List<Salary> salaries) {
		for(Salary mySalary:salaries){
			//检查这条记录是否存在，存在就跳过，不存在就添加
			Salary checkSalary=salaryMapper.checkSalaryByIYM(mySalary.getStuffid(), mySalary.getYear(), mySalary.getMonth());
			if(checkSalary==null){//如果为空的话，证明不存在，添加进去
				int i=salaryMapper.insertSelective(mySalary);
				if(i==1){//添加成功
					logger.info("为"+mySalary.getId()+"添加成功,现在时间是："+(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date())  );
					continue;
				}else{//添加失败
					logger.error("添加失败！salaryupdate");
					StampUtil.sendEmailToUser("salaryupdate");
					throw new RuntimeException("添加员工工资出错，将发送邮件给管理员，并进行事物回滚！"+(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
				}
			}else{//存在，进入下一个循环
				logger.info("ID为"+checkSalary.getId()+"已经存在，现在时间是："+(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) );
				continue;
			}
		}//end for
		
	}
	
	

	
}

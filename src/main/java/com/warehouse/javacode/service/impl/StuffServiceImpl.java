package com.warehouse.javacode.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.warehouse.javacode.dao.SalaryMapper;
import com.warehouse.javacode.dao.SalaryminusMapper;
import com.warehouse.javacode.dao.SalaryplusMapper;
import com.warehouse.javacode.dao.StuffMapper;
import com.warehouse.javacode.domain.Salary;
import com.warehouse.javacode.domain.Salaryminus;
import com.warehouse.javacode.domain.Salaryplus;
import com.warehouse.javacode.domain.Stuff;
import com.warehouse.javacode.domain.extend.StuffSalary;
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
	
	@Resource
	private SalaryplusMapper salaryplusMapper;
	
	@Resource
	private SalaryminusMapper salaryminusMapper;
	
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
			//添加员工信息的同时，初始化员工的工资信息
			stuff.setId(UUIDUtil.getUUID());
			i=stuffMapper.insertSelective(stuff);
			if(i==1){
				Salary salary=new Salary();
				salary.setStuffid(stuff.getId());
				salary.setId(UUIDUtil.getUUID());
				salary.setShould(stuff.getBasesalary());
				Calendar calendar=Calendar.getInstance();
				salary.setYear(calendar.get(Calendar.YEAR));
				salary.setMonth(calendar.get(Calendar.MONTH)+1);
				int j=salaryMapper.updateByPrimaryKeySelective(salary);
				if(j==1){
					i=j;
				}else{
					throw new RuntimeException("添加员工时，初始化工资信息失败");
				}
				
			}
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

	@Override
	public StuffSalary getStuffSalaryDetail(String id) {
		//根据工资ID获得工资详细信息,没有包括奖金和扣除的信息
		StuffSalary salaryDetail= salaryMapper.getSalaryDetailById(id);
		return salaryDetail;
	}

	@Override
	public List<Salaryplus> getSalaryPlusBySalaryId(String id) {
		
		List<Salaryplus> salaryplus=salaryplusMapper.getSalaryPlusBySalaryId(id);		
		return salaryplus;
	}

	@Override
	public List<Salaryminus> getSalartMinusBySalaryId(String id) {
		List<Salaryminus> salaryminus= salaryminusMapper.getSalaryMinusBySalaryId(id);
		
		return salaryminus;
	}

	@Override
	public int deleteSalaryPlusById(String id) {
		//通过ID，获得minus这一条信息
		Salaryplus salaryplus=salaryplusMapper.selectByPrimaryKey(id);
		String salaryId=salaryplus.getSalaryid();
		//再根据这条记录的价格，更新salary的价格
		BigDecimal plusDecimal=salaryplus.getPlusmoney();
		Salary salary=salaryMapper.selectByPrimaryKey(salaryId);
		salary.setShouldplus(salary.getShouldplus().subtract(plusDecimal));
		salary.setShould(salary.getShould().subtract(plusDecimal));
		salary.setBalance(salary.getShould().subtract(salary.getActual()));
		int j=salaryMapper.updateByPrimaryKeySelective(salary);
		int i=0;
		if(j==1){
			i=salaryplusMapper.deletePlusById(id);
			if(i==0){
				throw new RuntimeException("删除奖金失败了");
			}
		}
		return i;
	}

	@Override
	public int deleteSalaryMinusById(String id) {
		//通过ID，获得minus这一条信息
		Salaryminus salaryminus=salaryminusMapper.selectByPrimaryKey(id);
		String salaryId=salaryminus.getSalaryid();
		//再根据这条记录的价格，更新salary的价格
		BigDecimal minusDecimal=salaryminus.getMinusmoney();
		Salary salary=salaryMapper.selectByPrimaryKey(salaryId);
		salary.setShouldminus(salary.getShouldminus().subtract(minusDecimal));
		salary.setShould(salary.getShould().add(minusDecimal));
		salary.setBalance(salary.getShould().subtract(salary.getActual()));
		int j=salaryMapper.updateByPrimaryKeySelective(salary);
		int i=0;
		if(j==1){
			i=salaryminusMapper.deleteMinusById(id);
			if(i==0){
				throw new RuntimeException("删除扣除失败了");
			}
		}
		return i;
	}

	@Override
	public int addPlusEvent(String eventName, String eventMoney,String userId) {
		BigDecimal plusDecimal=new BigDecimal(eventMoney);
		Salary salary=salaryMapper.selectByPrimaryKey(userId);
		salary.setShouldplus(salary.getShouldplus().add(plusDecimal));
		salary.setShould(salary.getShould().add(salary.getShouldplus()));
		salary.setBalance(salary.getShould().subtract(salary.getActual()));
		int j=salaryMapper.updateByPrimaryKeySelective(salary);
		int i=0;
		if(j==1){
			Salaryplus salaryplus=new Salaryplus();
			salaryplus.setPlusid(UUIDUtil.getUUID());
			salaryplus.setPlusmoney(plusDecimal);
			salaryplus.setPlusname(eventName);
			salaryplus.setSalaryid(userId);
			i=salaryplusMapper.insertSelective(salaryplus);
			if(i==0){
				throw new RuntimeException("添加奖金失败了");
			}
		}
		return i;
	}

	@Override
	public int addMinusEvent(String eventName, String eventMoney,String userId) {
		BigDecimal minusDecimal=new BigDecimal(eventMoney);
		Salary salary=salaryMapper.selectByPrimaryKey(userId);
		salary.setShouldminus(salary.getShouldminus().add(minusDecimal));
		//当添加扣除事件时，should时指以前事件的总和，不再需要再加一次shouldPlus
//		salary.setShould(salary.getShould().add(salary.getShouldplus()).subtract(salary.getShouldminus()));
		salary.setShould(salary.getShould().subtract(salary.getShouldminus()));
		salary.setBalance(salary.getShould().subtract(salary.getActual()));
		int j=salaryMapper.updateByPrimaryKeySelective(salary);
		int i=0;
		if(j==1){
			Salaryminus salaryminus=new Salaryminus();
			salaryminus.setMinusid(UUIDUtil.getUUID());
			salaryminus.setMinusname(eventName);
			salaryminus.setMinusmoney(minusDecimal);
			salaryminus.setSalaryid(userId);
			i=salaryminusMapper.insertSelective(salaryminus);
			if (i==0) throw new RuntimeException("添加扣除失败");
		}
		return i;
	}

	@Override
	public List<Salary> getSalaryByYearAndMonth(int year, int month) {
		return salaryMapper.getSalaryByYearAndMonth(year,month);
	}

	@Override
	public int updateSalary(Salary salary) {

		return salaryMapper.updateByPrimaryKeySelective(salary);
	}
	
	@Override
	public int updateSalaryByDayOff(Salary salary,String baseSalary){
		BigDecimal basesalary=new BigDecimal(baseSalary);
		//请假天数的算法,返回值应该为请假后的基本工资,
		BigDecimal dayMinus=getDayOffMinusBaseSalary(salary.getDayoff(),basesalary);
		Salary salaryDetail=salaryMapper.selectByPrimaryKey(salary.getId());
		//基本工资加上奖励和扣除，就是应发工资
		salary.setShould(dayMinus.add(salaryDetail.getShouldplus()).subtract(salaryDetail.getShouldminus()));
		//应发-实发=结余,应发=基本工资+请假扣除的基本工资
		salary.setBalance(salary.getShould().subtract(salary.getActual()));
		int i=salaryMapper.updateByPrimaryKeySelective(salary);
		return i;
		
	}
	
	/*
	 * 一个月按30天算，请假一天扣除基本工资的30分之一，保留两位小数，四舍五入
	 */
	public BigDecimal getDayOffMinusBaseSalary(BigDecimal dayoff,BigDecimal baseSalary){
		BigDecimal returnSalary=baseSalary.multiply(new BigDecimal(30).subtract(dayoff)).divide(new BigDecimal(30),2,BigDecimal.ROUND_HALF_DOWN);
		return returnSalary;
	}

	
}

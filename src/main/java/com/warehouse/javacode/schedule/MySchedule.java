package com.warehouse.javacode.schedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.warehouse.javacode.domain.Salary;
import com.warehouse.javacode.domain.Stuff;
import com.warehouse.javacode.service.StuffService;
import com.warehouse.javacode.util.UUIDUtil;



@Component("taskSchedule")
public class MySchedule {
	
	@Resource
	private StuffService stuffService;
	
	/**
	 * 这里是根据员工的信息，在每月初的时候，自动添加员工的工资信息，
	 * 但这里需要注意的是，历史结余的问题还未解决
	 */
	@Scheduled(cron = "0 0/20 0 * * ?")
	public void getStuffSalaryByMonth(){
		//得到所有状态正常的员工
		List<Stuff> getAllStuffs=stuffService.getStatusNormalStuff();
		List<Salary> getSalariesByList=new ArrayList<Salary>(); 
		//遍历员工，方便得到员工的基本信息
		for(Stuff myStuff :getAllStuffs){
			//遍历员工的信息,将基本的工资信息装入实体对象中，
			//但是要查询到历史的结余是多少，这样方便进行管理
			Salary salary=new Salary();
			//salary Id
			salary.setId(UUIDUtil.getUUID()); 
			salary.setStuffid(myStuff.getId());
			salary.setShould(myStuff.getBasesalary());
//			salary.setActual(myStuff.getBasesalary());
			Calendar calendar=Calendar.getInstance();
			salary.setYear(calendar.get(Calendar.YEAR));
			salary.setMonth(calendar.get(Calendar.MONTH)+1);
			
			
			getSalariesByList.add(salary);
		}
		System.out.println("正在更新今日数据，请稍后。。。");
		//更新salary表中的信息
		stuffService.createSalaryByMonth(getSalariesByList);
	}
	
	/**
	 * 同步扣除和添加的钱
	 */
//	@Scheduled(cron = "0 0/20 0 * * ?")
//	public void getPlusAndMinusForSalary(){
//		Calendar calendar=Calendar.getInstance();
//		int year=calendar.get(Calendar.YEAR);
//		int month=calendar.get(Calendar.MONTH)+1;
//		//根据年月查询出当前的工资信息
//		List<Salary> salaries=stuffService.getSalaryByYearAndMonth(year,month);
//		//得到每一条工资的信息
//		for(Salary salary:salaries){
//			BigDecimal plusDecimal=new BigDecimal(0);
//			List<Salaryplus> salaryplus=stuffService.getSalaryPlusBySalaryId(salary.getId());
//			for(Salaryplus salaryplus2:salaryplus){
//				plusDecimal=plusDecimal.add(salaryplus2.getPlusmoney());
//			}
//			BigDecimal minusDecimal=new BigDecimal(0);
//			List<Salaryminus> salaryminus=stuffService.getSalartMinusBySalaryId(salary.getId());
//			for(Salaryminus salaryminus2:salaryminus){
//				minusDecimal=minusDecimal.add(salaryminus2.getMinusmoney());
//			}
//			if (salary.getShouldplus().compareTo(plusDecimal)!=0 || salary.getShouldminus().compareTo(minusDecimal)!=0){//不相等，所以需要更新
//				salary.setShouldplus(plusDecimal);
//				salary.setShouldminus(minusDecimal);
//				salary.setShould(salary.getShould().);
//				stuffService.updateSalary(salary);
//			}
//			System.out.println("相等,不用进行更新，id为:"+salary.getId());
//		}
//		
//	}

}

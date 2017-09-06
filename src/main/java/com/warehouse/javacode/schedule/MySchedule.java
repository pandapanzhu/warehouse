package com.warehouse.javacode.schedule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.warehouse.javacode.domain.Salary;
import com.warehouse.javacode.domain.Salaryminus;
import com.warehouse.javacode.domain.Salaryplus;
import com.warehouse.javacode.domain.Stuff;
import com.warehouse.javacode.service.StuffService;
import com.warehouse.javacode.util.UUIDUtil;



@Component("taskSchedule")
public class MySchedule {
	
	@Resource
	private StuffService stuffService;
	
	@Scheduled(cron = "0 0/10 23 * * ?")
	public void getStuffSalaryByMonth(){
		//得到所有状态正常的员工
		List<Stuff> getAllStuffs=stuffService.getStatusNormalStuff();
		List<Salary> getSalariesByList=new ArrayList<Salary>(); 
		//遍历员工，方便得到员工的基本信息
		for(Stuff myStuff :getAllStuffs){
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
	@Scheduled(cron = "0 0/20 0 * * ?")
	public void getPlusAndMinusForSalary(){
		Calendar calendar=Calendar.getInstance();
		int year=calendar.get(Calendar.YEAR);
		int month=calendar.get(Calendar.MONTH)+1;
		//根据年月查询出当前的工资信息
		List<Salary> salaries=stuffService.getSalaryByYearAndMonth(year,month);
		//得到每一条工资的信息
		for(Salary salary:salaries){
			BigDecimal plusDecimal=new BigDecimal(0);
			List<Salaryplus> salaryplus=stuffService.getSalaryPlusBySalaryId(salary.getId());
			for(Salaryplus salaryplus2:salaryplus){
				plusDecimal=plusDecimal.add(salaryplus2.getPlusmoney());
			}
			BigDecimal minusDecimal=new BigDecimal(0);
			List<Salaryminus> salaryminus=stuffService.getSalartMinusBySalaryId(salary.getId());
			for(Salaryminus salaryminus2:salaryminus){
				minusDecimal=minusDecimal.add(salaryminus2.getMinusmoney());
			}
			if (salary.getShouldplus().compareTo(plusDecimal)!=0 || salary.getShouldminus().compareTo(minusDecimal)!=0){//不相等，所以需要更新
				salary.setShouldplus(plusDecimal);
				salary.setShouldminus(minusDecimal);
				stuffService.updateSalary(salary);
			}
			System.out.println("相等,不用进行更新，id为:"+salary.getId());
		}
		
	}

}

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
			salary.setActual(myStuff.getBasesalary());
			Calendar calendar=Calendar.getInstance();
			salary.setYear(calendar.get(Calendar.YEAR));
			salary.setMonth(calendar.get(Calendar.MONTH)+1);
			getSalariesByList.add(salary);
		}
		System.out.println("正在更新今日数据，请稍后。。。");
		//更新salary表中的信息
		stuffService.createSalaryByMonth(getSalariesByList);
		
		
	}

}

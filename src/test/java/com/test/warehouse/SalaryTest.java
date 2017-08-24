package com.test.warehouse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.warehouse.javacode.domain.Salary;
import com.warehouse.javacode.domain.Stuff;
import com.warehouse.javacode.service.StuffService;
import com.warehouse.javacode.util.StampUtil;
import com.warehouse.javacode.util.UUIDUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-context.xml"})
public class SalaryTest extends AbstractJUnit4SpringContextTests{
	
	@Resource
	private StuffService stuffService;
	
	@Test
	public void testMail(){
		StampUtil.sendEmailToUser("salaryupdate");
	}
	

}
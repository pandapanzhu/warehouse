package com.warehouse.javacode.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.warehouse.javacode.domain.Salary;
import com.warehouse.javacode.domain.Salaryminus;
import com.warehouse.javacode.domain.Salaryplus;
import com.warehouse.javacode.domain.Stuff;
import com.warehouse.javacode.domain.extend.StuffSalary;
import com.warehouse.javacode.service.StuffService;
import com.warehouse.javacode.util.PageUtil;

@Controller
public class StuffController {
	
	private int pageSize=10;
	
	
	@Resource
	private StuffService stuffService;
	
	/**
	 * 显示员工的基本信息
	 * @param model
	 * @param pageNum
	 * @param search
	 * @return
	 */
	@RequestMapping("stuff/toShowStuff")
	public String toShowStuff(Model model,String pageNum,String search){
		int pageNo=1;
		if(StringUtils.isNotBlank(pageNum)){//如果PageNum不为空的话，就赋值
			pageNo=Integer.valueOf(pageNum);
		}
		PageUtil stuffPage=stuffService.getStuffList(search,pageNo,pageSize);
		//pageInfo=new PageInfo<>(stuffList);
		model.addAttribute("page", stuffPage);
		return "stuff/showStuff";
	}
	
	/**
	 * 查看，添加，修改共用同一个页面
	 * @param id
	 * @return
	 */
	@RequestMapping("stuff/toSaveOrupdateStuff")
	public String toSaveOrupdateStuff(Model model,String id){
		if(StringUtils.isNotBlank(id)){//如果Id不为空
			Stuff stuff=stuffService.getStuffById(id);
			model.addAttribute("stuff", stuff);
			model.addAttribute("pageType", "modify");
		}else{
			model.addAttribute("pageType", "add");
		}
		return "stuff/showStuffDetail";
	}
	
	/**
	 * 添加或修改员工信息
	 * @param stuff
	 * @return
	 */
	@RequestMapping("stuff/doSaveOrupdateStuff")
	@ResponseBody
	public String saveOrupdateStuff(Stuff stuff){
		JsonObject jsonObject =new JsonObject();
		int i=stuffService.saveOrUpdateStuff(stuff);
		if(i==1){
			jsonObject.addProperty("msg", "success");
		}else{
			jsonObject.addProperty("msg", "error");
		}
		return jsonObject.toString();
	}
	
	/**
	 * 员工离职后
	 * @param id
	 * @return
	 */
	@RequestMapping("stuff/leaveStuff")
	@ResponseBody
	public String stuffLeave(String id,int status){
		JsonObject jsonObject=new JsonObject();
		int i=stuffService.stuffleaveByStatus(id,status);
		if(i==1){
			jsonObject.addProperty("msg", "success");
		}else{
			jsonObject.addProperty("msg", "error");
		}
		return jsonObject.toString();
	}
	
	/**
	 * 删除员工
	 * @param id
	 * @return
	 */
	@RequestMapping("stuff/dltStuff")
	@ResponseBody
	public String dltStuf(String id){
		JsonObject jsonObject=new JsonObject();
		int i=stuffService.dltStuffById(id);
		if(i==1){
			jsonObject.addProperty("msg", "success");
		}else{
			jsonObject.addProperty("msg", "error");
		}
		return jsonObject.toString();
	}

	/**
	 * 获取工资信息
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("stuff/doShowStuffSalary")
	public String doShowStuffSalary(String pageNum,String date,String search,Model model){
		int year=2017;
		int month=01;
		if(StringUtils.isBlank(date)){
			Calendar calendar=Calendar.getInstance();
			month=calendar.get(Calendar.MONTH)+1;
			year=calendar.get(Calendar.YEAR);
		}else{
			String[] newDate=date.split("-");
			year=Integer.valueOf(newDate[0]);
			month=Integer.valueOf(newDate[1]);
		}
		int pageNo=1;
		if(StringUtils.isNotBlank(pageNum)){//如果不为空
			pageNo=Integer.valueOf(pageNum);//将字符串转化为数字
		}
		List<Stuff> stuffs=stuffService.getAllStuff();//得到全部员工的信息，拼凑下拉列表
		PageUtil salaryPage=stuffService.getStuffSalaryList(pageNo,pageSize,search,year,month);
		model.addAttribute("myYear", year);
		model.addAttribute("myMonth", month);
		model.addAttribute("stuffList", stuffs);
		model.addAttribute("page", salaryPage);
		return "stuff/showStuffSalary";
	}
	
	@RequestMapping("stuff/toSaveOrupdateStuffSalary")
	public String doShowStuffSalaryDetail(String id,Model model){
		//根据工资条的ID，获得一条工资的详细信息，不包括奖金和扣除的信息
		StuffSalary salaryDetail=stuffService.getStuffSalaryDetail(id);
		//根据ID，获得奖金的信息
		List<Salaryplus> plusDetail=stuffService.getSalaryPlusBySalaryId(id);
		DateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			for(Salaryplus plus:plusDetail){
				String dateString=sDateFormat.format(plus.getUpdatetime());
				plus.setRemark(dateString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		//根据Id,获得扣除的信息
		List<Salaryminus> minusDetail=stuffService.getSalartMinusBySalaryId(id);
		try {
			for(Salaryminus minus:minusDetail){
				String dateString=sDateFormat.format(minus.getUpdatetime());
				minus.setRemark(dateString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("detail", salaryDetail);//工资及员工信息
		model.addAttribute("plusDetail", plusDetail);//奖金信息
		model.addAttribute("minusDetail",minusDetail);
		return "stuff/showStuffSalaryDetail";
	}
	
	/**
	 * 删除奖金和扣钱的事件
	 */
	@RequestMapping("stuff/deleteEventById")
	@ResponseBody
	public String deleteEventById(String id,int type){
		JsonObject jsonObject=new JsonObject();
		int i=0;
		if (type==1) {//删除添加的事件
			i=stuffService.deleteSalaryPlusById(id);
		}else if(type==2){//删除扣除的事件
			i=stuffService.deleteSalaryMinusById(id);
		}
		if(i==0){
			jsonObject.addProperty("msg", "error");
		}else if(i==1){
			jsonObject.addProperty("msg", "success");
		}else {
			jsonObject.addProperty("msg", "wrong");
		}
		return jsonObject.toString();
	}
	
	@RequestMapping("stuff/addEvent")
	@ResponseBody
	public String addEvent(String type,String eventName,String eventMoney,String userId){
		JsonObject jsonObject=new JsonObject();
		int i=0;
		if(StringUtils.equals(type, "1")){
			i=stuffService.addPlusEvent(eventName,eventMoney,userId);
		}else if(StringUtils.equals(type, "2")){
			i=stuffService.addMinusEvent(eventName,eventMoney,userId);
		}else{
			jsonObject.addProperty("msg", "error");
		}
		if(i==1){
			jsonObject.addProperty("msg", "success");
		}else{
			jsonObject.addProperty("msg", "error");
		}
		return jsonObject.toString();
	}
	
	@RequestMapping("stuff/updateSalaryDetail")
	@ResponseBody
	public String updateSalaryDetail(Salary salary,String basesalary){
		JsonObject jsonObject=new JsonObject();
		int i=stuffService.updateSalaryByDayOff(salary,basesalary);
		if(i==1){
			jsonObject.addProperty("msg", "success");
		}else{
			jsonObject.addProperty("msg", "error");
		}
		return jsonObject.toString();
		
	}
}

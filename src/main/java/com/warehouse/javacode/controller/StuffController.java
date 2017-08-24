package com.warehouse.javacode.controller;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.warehouse.javacode.domain.Stuff;
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
	
	/*	@RequestMapping("doShowStuff")
	@ResponseBody
	public PageInfo<T> doShowStuff(int pageSize,int pageNum){
		PageHelper.startPage(pageNum, pageSize);//分页开始
		
	}*/
	
}

package com.warehouse.javacode.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping("page/toLogin")
	public String toLoginJsp(){
		return "redirect:/login.jsp";
	}
	
	@RequestMapping("page/toLogOut")
	public String toLogOut(HttpSession session){
		session.removeAttribute("loginSession");
		return "redirect:/login.jsp";
	}
	
	@RequestMapping("page/toIndex")
	public String toIndex(){
		return "index";
	}
	/**
	 * 显示员工的基本信息
	 * @return
	 */
	@RequestMapping("toShowStuff")
	public String toShowStuff(){
		return "showStuff";
	}
	
	/**
	 * 进入到工资查询的页面
	 * @return
	 */
	@RequestMapping("toShowStuffSalary")
	public String toShowStuffSalary(){
		return "admin/showStuffSalary";
	}

}

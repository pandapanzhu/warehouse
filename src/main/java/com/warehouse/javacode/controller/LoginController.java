package com.warehouse.javacode.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.warehouse.javacode.domain.Login;
import com.warehouse.javacode.service.LoginService;
import com.warehouse.javacode.util.MD5Util;

@Controller
public class LoginController {
	
	@Resource
	private LoginService loginService;
	
	@RequestMapping("login/doLogin")
	@ResponseBody
	public String doLogin(String loginname,String password,HttpSession session){
		JsonObject jsonObject=new JsonObject();
		String md5Pass=MD5Util.GetMD5Code(password);
		Login loginCheck=loginService.loginByNameAndPass(loginname, md5Pass);
		if(loginCheck==null){
			jsonObject.addProperty("loginStatus", false);
		}else{
			loginCheck.setPassword("");//清除密码
			session.setAttribute("loginSession", loginCheck);
			session.setMaxInactiveInterval(3600);
			jsonObject.addProperty("loginStatus", true);
		}
		return jsonObject.toString();
	}
}

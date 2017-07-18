package com.warehouse.javacode.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

public class LoginCheckFilter implements Filter{

	@SuppressWarnings("unused")
	private FilterConfig filterCon = null;
	
	public void init(FilterConfig config) throws ServletException {
		filterCon = config;
	}
	
	/**
	 * 在经过过滤器之前的操作
	 */
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) arg0;
		HttpServletResponse response=(HttpServletResponse) arg1;
		String reqUrl=request.getRequestURI();
		if(reqUrl.contains("/login/doLogin") ||reqUrl.contains("page/toLogin")){//如果是登录的请求，就直接放行
			filterChain.doFilter(arg0, arg1);
		}else{
			// 在进行登录之前，首先获取登录的token，判断他是否登录过==此为SSO的登录校验
			String loginUrl=request.getContextPath()+"/rest/page/toLogin";//系统首页
			if(request.getSession().getAttribute("loginSession")!=null){//如果session可以用
				filterChain.doFilter(arg0, arg1);
			}else{//没有获取到session
				response.sendRedirect(loginUrl);//指向登录的url
			}// end else if
		}//end else for 1

	}

	@Override
	public void destroy() {
		filterCon=null;
	}

	

}

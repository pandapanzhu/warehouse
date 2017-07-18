package com.warehouse.javacode.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.warehouse.javacode.dao.LoginMapper;
import com.warehouse.javacode.domain.Login;
import com.warehouse.javacode.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Resource
	private LoginMapper loginMapper;

	@Override
	public Login loginByNameAndPass(String username, String password) {
		Login login=loginMapper.selectByNameAndPass(username, password);
		if(login!=null){
			return login;
		}
		return null;
	}

}

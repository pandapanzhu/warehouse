package com.warehouse.javacode.service;

import com.warehouse.javacode.domain.Login;

public interface LoginService {
	public Login loginByNameAndPass(String username,String password);
}

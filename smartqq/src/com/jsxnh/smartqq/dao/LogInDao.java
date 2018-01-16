package com.jsxnh.smartqq.dao;

import java.util.List;

import com.jsxnh.smartqq.entities.LogIn;

public interface LogInDao {

	//????????
	public void addLogIn(LogIn login);
	//?????????????
	public List<LogIn> findLogInMessage(Integer user_id);
	//???
	public void addLogout(Integer user_id);
}

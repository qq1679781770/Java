package com.jsxnh.smartqq.dao;

import java.util.List;

import com.jsxnh.smartqq.entities.LogIn;

public interface LogInDao {

	//添加登录信息
	public void addLogIn(LogIn login);
	//查找用户登录信息
	public List<LogIn> findLogInMessage(Integer user_id);
	//登出
	public void addLogout(Integer user_id);
}

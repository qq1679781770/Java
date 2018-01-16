package com.jsxnh.smartqq.dao;

import com.jsxnh.smartqq.entities.PasswordAnswer;

public interface PasswordAnswerDao {

	//添加密保问题
	public void addPasswordAnswer(PasswordAnswer password_answer);
	//确认密保问题
	public boolean checkProblem(PasswordAnswer password_answer);
	//查找密保问题
	public PasswordAnswer findPasswordAnswer(Integer user_id);
}

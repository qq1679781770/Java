package com.jsxnh.smartqq.dao;

import java.util.List;

import com.jsxnh.smartqq.entities.LogIn;

public interface LogInDao {

	//��ӵ�¼��Ϣ
	public void addLogIn(LogIn login);
	//�����û���¼��Ϣ
	public List<LogIn> findLogInMessage(Integer user_id);
	//�ǳ�
	public void addLogout(Integer user_id);
}

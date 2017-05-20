package com.jsxnh.smartqq.dao;

import com.jsxnh.smartqq.entities.PasswordAnswer;

public interface PasswordAnswerDao {

	//����ܱ�����
	public void addPasswordAnswer(PasswordAnswer password_answer);
	//ȷ���ܱ�����
	public boolean checkProblem(PasswordAnswer password_answer);
	//�����ܱ�����
	public PasswordAnswer findPasswordAnswer(Integer user_id);
}

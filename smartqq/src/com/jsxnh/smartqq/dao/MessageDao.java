package com.jsxnh.smartqq.dao;

import java.util.List;

import com.jsxnh.smartqq.entities.Message;

public interface MessageDao {

	//��������¼
	public void addMessage(Message message);
	//���������¼
	public List<Message> findMessages(Integer send_id,Integer receive_id);
	
}

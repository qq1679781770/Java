package com.jsxnh.smartqq.dao;

import java.util.List;

import com.jsxnh.smartqq.entities.Message;

public interface MessageDao {

	//添加聊天记录
	public void addMessage(Message message);
	//查找聊天记录
	public List<Message> findMessages(Integer send_id,Integer receive_id);
	
}

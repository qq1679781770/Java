package com.jsxnh.smartqq.dao;

import java.util.List;

import com.jsxnh.smartqq.entities.TemporaryMessage;

public interface TemporaryMessageDao {

	//添加临时信息
	public void addMessage(TemporaryMessage temporarymessage);
	//接受信息时删除记录
	public void deleteMessage(TemporaryMessage temporarymessage);
	//查找用户接收的消息
	public List<TemporaryMessage> findMessages(Integer receive_id);
	
	public TemporaryMessage findMessage(Integer user1_id,Integer user2_id);
	
	public void deleteTemporaryMessage(TemporaryMessage temporarymessage);
	
}

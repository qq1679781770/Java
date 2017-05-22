package com.jsxnh.smartqq.service;

import java.util.Date;
import java.util.List;

import com.jsxnh.smartqq.entities.TemporaryMessage;

public interface ChatService {

	//������Ϣ
	public void sendMessage(Integer send_id,Integer receive_id,String content);
	//������Ϣ
	public List<TemporaryMessage> receiveMessage(Integer receive_id); 
	
	public TemporaryMessage receiveMessage(Integer send_id,Integer receive_id);
	
	public boolean findTemporaryMessage(Integer send_id,Integer receive_id);
	
	public void saveMessage(Integer user1_id,Integer user2_id,String content,Date send_date,Date receive_date);
	
	public List<TemporaryMessage> findMessages(Integer receive_id);
}

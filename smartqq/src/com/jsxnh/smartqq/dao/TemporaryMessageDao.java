package com.jsxnh.smartqq.dao;

import java.util.List;

import com.jsxnh.smartqq.entities.TemporaryMessage;

public interface TemporaryMessageDao {

	//�����ʱ��Ϣ
	public void addMessage(TemporaryMessage temporarymessage);
	//������Ϣʱɾ����¼
	public void deleteMessage(TemporaryMessage temporarymessage);
	//�����û����յ���Ϣ
	public List<TemporaryMessage> findMessages(Integer receive_id);
	
	public TemporaryMessage findMessage(Integer user1_id,Integer user2_id);
	
	public void deleteTemporaryMessage(TemporaryMessage temporarymessage);
	
}

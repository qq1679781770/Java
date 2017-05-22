package com.jsxnh.smartqq.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsxnh.smartqq.dao.MessageDao;
import com.jsxnh.smartqq.dao.TemporaryMessageDao;
import com.jsxnh.smartqq.entities.Message;
import com.jsxnh.smartqq.entities.TemporaryMessage;
import com.jsxnh.smartqq.service.ChatService;

@Service
public class ChatServiceImpl implements ChatService{

	@Autowired
	MessageDao messageDao;
	
	@Autowired
	TemporaryMessageDao temporaryMessageDao;

	@Override
	public void sendMessage(Integer send_id, Integer receive_id, String content) {
		TemporaryMessage temporaryMessage=new TemporaryMessage();
		temporaryMessage.setContent(content);
		temporaryMessage.setSend_id(send_id);
		temporaryMessage.setReceive(receive_id);
		temporaryMessage.setSend_time(new Date());
		temporaryMessageDao.addMessage(temporaryMessage);
	}

	@Override
	public List<TemporaryMessage> receiveMessage(Integer receive_id) {
		List<TemporaryMessage> temporaryMessages=temporaryMessageDao.findMessages(receive_id);
		for(TemporaryMessage temmessage:temporaryMessages){
			Message message=new Message();
			message.setContent(temmessage.getContent());
			message.setSend_id(temmessage.getSend_id());
			message.setSend_time(temmessage.getSend_time());
			message.setReceive_id(receive_id);
			message.setReceive_time(new Date());
			messageDao.addMessage(message);
		}
		TemporaryMessage temporaryMessage=new TemporaryMessage();
		temporaryMessage.setReceive(receive_id);
		temporaryMessageDao.deleteMessage(temporaryMessage);
		return temporaryMessages;
	}

	@Override
	public TemporaryMessage receiveMessage(Integer send_id, Integer receive_id) {
		TemporaryMessage temporaryMessage= temporaryMessageDao.findMessage(send_id, receive_id);
		Message message=new Message();
		message.setContent(temporaryMessage.getContent());
		message.setSend_id(temporaryMessage.getSend_id());
		message.setSend_time(temporaryMessage.getSend_time());
		message.setReceive_id(receive_id);
		message.setReceive_time(new Date());
		messageDao.addMessage(message);
		temporaryMessageDao.deleteTemporaryMessage(temporaryMessage);
		return temporaryMessage;
	}

	@Override
	public boolean findTemporaryMessage(Integer send_id, Integer receive_id) {
		if(temporaryMessageDao.findMessage(send_id,receive_id)!=null)
			return true;
		return false;
	}

	@Override
	public void saveMessage(Integer user1_id, Integer user2_id, String content,Date send_date,Date receive_date) {
		Message message=new Message();
		message.setSend_id(user1_id);
		message.setReceive_id(user2_id);
		message.setContent(content);
		message.setSend_time(send_date);
		message.setReceive_time(receive_date);
		messageDao.addMessage(message);
	}

	@Override
	public List<TemporaryMessage> findMessages(Integer receive_id) {
		return temporaryMessageDao.findMessages(receive_id);
	}
	
	
}

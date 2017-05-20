package com.jsxnh.smartqq.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsxnh.smartqq.dao.MessageDao;
import com.jsxnh.smartqq.entities.Message;

@Repository
public class MessageImpl implements MessageDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void addMessage(Message message) {
		getSession().save(message);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> findMessages(Integer send_id, Integer receive_id) {
		String hql="FROM Message a WHERE a.send_id=? AND a.receive_id=?";
		return getSession().createQuery(hql).setParameter(0, send_id).setParameter(1,receive_id).list();
	}
}

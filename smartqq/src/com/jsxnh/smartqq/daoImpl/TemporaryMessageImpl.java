package com.jsxnh.smartqq.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsxnh.smartqq.dao.TemporaryMessageDao;
import com.jsxnh.smartqq.entities.TemporaryMessage;

@Repository
public class TemporaryMessageImpl implements TemporaryMessageDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void addMessage(TemporaryMessage temporarymessage) {
	    getSession().save(temporarymessage);	
	}

	@Override
	public void deleteMessage(TemporaryMessage temporarymessage) {
		String hql="DELETE FROM TemporaryMessage a WHERE a.receive=?";
		getSession().createQuery(hql).setParameter(0, temporarymessage.getReceive()).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TemporaryMessage> findMessages(Integer receive_id) {
		String hql="FROM TemporaryMessage a WHERE a.receive=?";
		return getSession().createQuery(hql).setParameter(0, receive_id).list();
	}

	@Override
	public TemporaryMessage findMessage(Integer user1_id, Integer user2_id) {
		String hql="FROM TemporaryMessage a WHERE a.send_id=? AND a.receive=?";
		return (TemporaryMessage) getSession().createQuery(hql).setParameter(0, user1_id).setParameter(1,user2_id).uniqueResult();
	}

	@Override
	public void deleteTemporaryMessage(TemporaryMessage temporarymessage) {
		String hql="DELETE FROM TemporaryMessage a WHERE a.id=?";
		getSession().createQuery(hql).setParameter(0, temporarymessage.getId()).executeUpdate();
	}

}

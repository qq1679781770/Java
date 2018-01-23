package com.jsxnh.smartqq.daoImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		String hql="FROM TemporaryMessage a WHERE a.receive=? order by a.send_time asc";
		return getSession().createQuery(hql).setParameter(0, receive_id).list();
	}

	@Override
	public TemporaryMessage findMessage(Integer user1_id, Integer user2_id,String send_time) {
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = null;
		try {
			time = df.parse(send_time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String hql="FROM TemporaryMessage a WHERE a.send_id=? AND a.receive=? AND a.send_time=?";
		return (TemporaryMessage) getSession().createQuery(hql).setParameter(0, user1_id).setParameter(1,user2_id).setParameter(2,time).uniqueResult();
	}



	@Override
	public void deleteTemporaryMessage(TemporaryMessage temporarymessage) {
		String hql="DELETE FROM TemporaryMessage a WHERE a.id=?";
		getSession().createQuery(hql).setParameter(0, temporarymessage.getId()).executeUpdate();
	}

	@Override
	public TemporaryMessage findMessageById(Integer id) {
		String hql = "from TemporaryMessage a WHERE a.id=?";
		return (TemporaryMessage) getSession().createQuery(hql).setParameter(0, id).uniqueResult();
	}

}

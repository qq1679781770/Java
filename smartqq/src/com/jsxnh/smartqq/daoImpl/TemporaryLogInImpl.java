package com.jsxnh.smartqq.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsxnh.smartqq.dao.TemporaryLogInDao;
import com.jsxnh.smartqq.entities.TemporaryLogIn;

@Repository
public class TemporaryLogInImpl implements TemporaryLogInDao{

	@Autowired
	private SessionFactory  sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void addTemporaryLogIn(TemporaryLogIn temporylogin) {
		getSession().save(temporylogin);
	}

	@Override
	public void deleteTemporaryLogIn(Integer user_id) {
		String hql="DELETE FROM TemporaryLogIn a WHERE a.user_id=?";
		getSession().createQuery(hql).setParameter(0, user_id).executeUpdate();
	}

	@Override
	public TemporaryLogIn findlogIn(Integer user_id) {
		String hql="FROM TemporaryLogIn a WHERE a.user_id=?";
		return (TemporaryLogIn) getSession().createQuery(hql).setParameter(0, user_id).uniqueResult();
	}

}

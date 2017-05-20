package com.jsxnh.smartqq.daoImpl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsxnh.smartqq.dao.LogInDao;
import com.jsxnh.smartqq.entities.LogIn;

@Repository
public class LogInImpl implements LogInDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	@Override
	public void addLogIn(LogIn login) {
		getSession().save(login);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LogIn> findLogInMessage(Integer user_id) {
		String hql="FROM LogIn a WHERE a.user_id=?";
		return getSession().createQuery(hql).setParameter(0, user_id).list();
	}
	@Override
	public void addLogout(Integer user_id) {
		String hql="UPDATE LogIn a SET a.logout_time=? WHERE a.user_id=?";
		getSession().createQuery(hql).setParameter(0,new Date()).setParameter(1, user_id).executeUpdate();
		
	}

}

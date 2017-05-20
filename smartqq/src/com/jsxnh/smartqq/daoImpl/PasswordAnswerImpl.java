package com.jsxnh.smartqq.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsxnh.smartqq.dao.PasswordAnswerDao;
import com.jsxnh.smartqq.entities.PasswordAnswer;

@Repository
public class PasswordAnswerImpl implements PasswordAnswerDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	@Override
	public void addPasswordAnswer(PasswordAnswer password_answer) {
		getSession().save(password_answer);
	}

	@Override
	public boolean checkProblem(PasswordAnswer password_answer) {
		String hql="FROM PasswordAnswer a WHERE a.user_id=? AND a.answer=?";
		PasswordAnswer  result=(PasswordAnswer) getSession().createQuery(hql).setParameter(0, password_answer.getUser_id())
				                                            .setParameter(1, password_answer.getAnswer()).uniqueResult();
		if(result==null)
		    return false;
		else
			return true;
	}
	@Override
	public PasswordAnswer findPasswordAnswer(Integer user_id) {
		String hql="FROM PasswordAnswer a WHERE a.user_id=?";
		return (PasswordAnswer) getSession().createQuery(hql).setParameter(0, user_id).uniqueResult();
	}

}

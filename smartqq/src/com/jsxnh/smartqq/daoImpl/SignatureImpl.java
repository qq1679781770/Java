package com.jsxnh.smartqq.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsxnh.smartqq.dao.SignatureDao;
import com.jsxnh.smartqq.entities.Signature;

@Repository
public class SignatureImpl implements SignatureDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addSigatureDao(Signature signature) {
		getSession().save(signature);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Signature> findSignatureByid(Integer user_id) {
		String hql="FROM Signature a WHERE a.user_id=?";
		return getSession().createQuery(hql).setParameter(0, user_id).list();
	}
	
	
}

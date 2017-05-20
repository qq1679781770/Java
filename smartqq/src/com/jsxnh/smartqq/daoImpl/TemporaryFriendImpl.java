package com.jsxnh.smartqq.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsxnh.smartqq.dao.TemporaryFriendDao;
import com.jsxnh.smartqq.entities.TemporaryFriend;

@Repository
public class TemporaryFriendImpl implements TemporaryFriendDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void addTemporaryFriend(TemporaryFriend temporaryFriend) {
		getSession().save(temporaryFriend);
	}

	@Override
	public void deleteTemporaryFriend(Integer user1_id, Integer user2_id) {
		String hql="DELETE FROM TemporaryFriend a WHERE a.user1_id=? AND a.user2_id=?";
		getSession().createQuery(hql).setParameter(0, user1_id).setParameter(1, user2_id).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TemporaryFriend> findTemporaryFriends(Integer user_id) {
		String hql="FROM TemporaryFriend a WHERE a.user2_id=?";
		return getSession().createQuery(hql).setParameter(0, user_id).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TemporaryFriend> findTemporaryFriend(Integer user1_id, Integer user2_id) {
		String hql="FROM TemporaryFriend a WHERE a.user1_id=?  AND a.user2_id=?";
		return (List<TemporaryFriend>) getSession().createQuery(hql).setParameter(0, user1_id).setParameter(1, user2_id).list();
	}

}

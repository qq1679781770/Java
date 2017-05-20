package com.jsxnh.smartqq.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsxnh.smartqq.dao.UserDao;
import com.jsxnh.smartqq.entities.Packet;
import com.jsxnh.smartqq.entities.User;

@Repository
public class UserImpl implements UserDao{

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	@Override
	public User findbyid(Integer user_id) {
		String hql="FROM User a WHERE a.user_id=?";
		return (User) getSession().createQuery(hql).setParameter(0, user_id).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findbyname(String nickname) {
		String hql="FROM User a where a.nick_name like ?";
		return getSession().createQuery(hql).setParameter(0, "%"+nickname+"%").list();
	}

	@Override
	public void register(User user) {
		user.setStatus(0);
		getSession().save(user);
	}

	@Override
	public int updatestatus(User user) {
		String hql1="FROM User a WHERE a.user_id=?";
		User user_=(User) getSession().createQuery(hql1).setParameter(0, user.getUser_id()).uniqueResult();
		String hql2="UPDATE User a SET a.status=? where a.user_id=?";
		if(user_.getStatus()==null)
			return 0;
		Integer status=user_.getStatus();
		if(status==0){
			getSession().createQuery(hql2).setParameter(0, 1).setParameter(1, user.getUser_id()).executeUpdate();
		}
		else{
			getSession().createQuery(hql2).setParameter(0, 0).setParameter(1, user.getUser_id()).executeUpdate();
		}
		return 1;
	}
	
	@Override
	public int updateNickName(User user) {
		String hql="UPDATE User a SET a.nick_name=? where a.user_id=?";
		getSession().createQuery(hql).setParameter(0, user.getNick_name())
		            .setParameter(1, user.getUser_id())
		            .executeUpdate();
		return 1;
	}
	@Override
	public int updateSignature(User user) {
		String hql="UPDATE User a SET a.signature=? WHERE a.user_id=?";
		getSession().createQuery(hql).setParameter(0, user.getSignature())
		            .setParameter(1, user.getUser_id())
		            .executeUpdate();
		return 1;
	}
	@Override
	public int updatePassword(User user) {
		String hql="UPDATE User a SET a.password=? WHERE a.user_id=?";
		getSession().createQuery(hql).setParameter(0, user.getPassword())
		            .setParameter(1, user.getUser_id())
		            .executeUpdate();
		return 1;
	}
	@Override
	public int updateData(User user) {
		String hql="UPDATE User a SET a.name=?,a.age=?,a.message=? WHERE a.user_id=?";
		getSession().createQuery(hql).setParameter(0, user.getName())
		            .setParameter(1, user.getAge())
		            .setParameter(2, user.getMessage())
		            .setParameter(3, user.getUser_id())
		            .executeUpdate();
		return 1;
	}
	@Override
	public int addPacket(User user, Packet packet) {
		User user_=getSession().get(User.class, user.getId());
		user_.getPackets().add(packet);
		getSession().save(packet);
		return 1;
	}
	@Override
	public int deletePacket(User user, Packet packet) {
		User user_=getSession().get(User.class, user.getId());
		user_.getPackets().remove(packet);
		String hql="DELETE FROM Packet a WHERE a.id=?";
		getSession().createQuery(hql).setParameter(0, packet.getId()).executeUpdate();
		return 1;
	}
	@Override
	public boolean checkPassword(User user) {
		String hql="FROM User a WHERE a.user_id=? AND a.password=?";
		User user_=(User) getSession().createQuery(hql).setParameter(0, user.getUser_id())
				                      .setParameter(1, user.getPassword()).uniqueResult();
		if(user_==null)
		    return false;
		else
			return true;
	}
}

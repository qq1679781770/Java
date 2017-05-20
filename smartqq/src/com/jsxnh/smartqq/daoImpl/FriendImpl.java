package com.jsxnh.smartqq.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsxnh.smartqq.dao.FriendDao;
import com.jsxnh.smartqq.entities.Friend;
import com.jsxnh.smartqq.entities.Packet;
import com.jsxnh.smartqq.entities.User;

@Repository
public class FriendImpl implements FriendDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Friend> findFriends(User user, Packet packet) {
		String hql1="FROM Friend a WHERE a.user1_id=:user_id AND a.packet1=:packet";
		String hql2="FROM Friend a WHERE a.user2_id=:user_id AND a.packet2=:packet";
		List<Friend> friends=new ArrayList<Friend>();
		friends.addAll(getSession().createQuery(hql1).setParameter("user_id", user.getUser_id())
				                           .setParameter("packet", packet).list());
	    friends.addAll(getSession().createQuery(hql2).setParameter("user_id", user.getUser_id())
	    		                          .setParameter("packet", packet).list());
		return friends;
	}

	@Override
	public void addFriend(User user1, User user2, Packet packet1, Packet packet2) {
		Friend friend=new Friend();
		friend.setUser1_id(user1.getUser_id());
		friend.setUser2_id(user2.getUser_id());
        friend.setPacket1(packet1);
        friend.setPacket2(packet2);
        getSession().save(friend);
	}

	@Override
	public void deleteFriend(User user1, User user2) {
		Friend friend=findFriend(user1, user2);
		String hql="DELETE FROM Friend a WHERE a.id=?";
		getSession().createQuery(hql).setParameter(0, friend.getId()).executeUpdate();
	}

	@Override
	public void movePacket(User user,Packet old_packet, Packet packet) {
		String hql1="FROM Friend a WHERE a.user1_id=? AND a.packet2=?";
		String hql2="FROM Friend a WHERE a.user2_id=? AND a.packet1=?";
		String hql3="UPDATE Friend a SET a.packet1=? WHERE a.id=?";
		String hql4="UPDATE Friend a SET a.packet2=? WHERE a.id=?";
		Friend friend=(Friend) getSession().createQuery(hql1).setParameter(0, user.getUser_id())
                                           .setParameter(1, old_packet).uniqueResult();
		if(friend!=null){
			getSession().createQuery(hql4).setParameter(0, packet)
			            .setParameter(1, friend.getId()).executeUpdate();
			return;
		}
		friend=(Friend) getSession().createQuery(hql2).setParameter(0, user.getUser_id())
                .setParameter(1, old_packet).uniqueResult();
		if(friend!=null){
			getSession().createQuery(hql3).setParameter(0, packet)
                        .setParameter(1, friend.getId()).executeUpdate();
			return;
		}
	}

	@Override
	public Friend findFriend(User user1, User user2) {
		String hql="FROM Friend a WHERE a.user1_id=? AND a.user2_id=?";
		Friend friend=(Friend) getSession().createQuery(hql).setParameter(0, user1.getUser_id())
				                           .setParameter(1, user2.getUser_id()).uniqueResult();
		if(friend!=null)
			return friend;
		friend=(Friend) getSession().createQuery(hql).setParameter(0, user2.getUser_id())
				                    .setParameter(1, user1.getUser_id()).uniqueResult();
		if(friend!=null)
			return friend;
		return null;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Friend> findAllFriends(User user) {
		String hql1="FROM Friend a WHERE a.user1_id=?";
		String hql2="FROM Friend a WHERE a.user2_id=?";
		List<Friend> friends=new ArrayList<Friend>();
		friends.addAll(getSession().createQuery(hql1).setParameter(0, user.getUser_id()).list());
		friends.addAll(getSession().createQuery(hql2).setParameter(0, user.getUser_id()).list());
		return friends;
	}

	@Override
	public void modifyRemarkName(User user1, User user2, String remarkname) {
		String hql="FROM Friend a WHERE a.user1_id=? AND a.user2_id=?";
		Friend friend=null;
		friend=(Friend) getSession().createQuery(hql).setParameter(0, user1.getUser_id())
				                    .setParameter(1, user2.getUser_id()).uniqueResult();
		if(friend!=null){
			String hql1="UPDATE Friend a SET a.remark1_name=? WHERE a.id=?";
			getSession().createQuery(hql1).setParameter(0, remarkname)
			                              .setParameter(1, friend.getId())
			                              .executeUpdate();
			return;
		}
		friend=(Friend) getSession().createQuery(hql).setParameter(0, user2.getUser_id())
				           .setParameter(1, user1.getUser_id())
				           .uniqueResult();
		
		if(friend!=null){
			String hql1="UPDATE Friend a SET a.remark2_name=? WHERE a.id=?";
			getSession().createQuery(hql1).setParameter(0, remarkname)
			                              .setParameter(1, friend.getId())
			                              .executeUpdate();
			return;
		}
		return;
	}

}

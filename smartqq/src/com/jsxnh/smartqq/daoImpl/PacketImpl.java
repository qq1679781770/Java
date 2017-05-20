package com.jsxnh.smartqq.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsxnh.smartqq.dao.PacketDao;
import com.jsxnh.smartqq.entities.Friend;
import com.jsxnh.smartqq.entities.Packet;
import com.jsxnh.smartqq.entities.User;

@Repository
public class PacketImpl implements PacketDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Packet> findpacketById(User user) {
		String hql="FROM Packet a WHERE a.user=?";
		return getSession().createQuery(hql).setParameter(0, user).list();
	}

	@Override
	public void modifyPacket(Packet packet, String new_name) {
		String hql="UPDATE Packet a SET a.packet_name=? WHERE a.id=?";
		getSession().createQuery(hql).setParameter(0, new_name).setParameter(1, packet.getId())
		            .executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Friend> findFriendsbyPacket(Packet packet) {
		String hql1="FROM Friend a WHERE a.packet1=?";
		String hql2="FROM Friend a WHERE a.packet2=?";
		List<Friend> friends=null;
		friends=getSession().createQuery(hql1).setParameter(0, packet).list();
		friends.addAll(getSession().createQuery(hql2).setParameter(0, packet).list());
		return friends;
	}

}

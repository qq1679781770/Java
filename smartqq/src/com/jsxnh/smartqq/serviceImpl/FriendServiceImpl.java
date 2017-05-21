package com.jsxnh.smartqq.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jsxnh.smartqq.dao.FriendDao;
import com.jsxnh.smartqq.dao.TemporaryFriendDao;
import com.jsxnh.smartqq.dao.UserDao;
import com.jsxnh.smartqq.entities.Friend;
import com.jsxnh.smartqq.entities.Packet;
import com.jsxnh.smartqq.entities.TemporaryFriend;
import com.jsxnh.smartqq.entities.User;
import com.jsxnh.smartqq.service.FriendService;

@Service
public class FriendServiceImpl implements FriendService{

	@Autowired
	FriendDao friendDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	TemporaryFriendDao temporaryFriendDao;

	@Override
	public void addFriend(Integer user1_id, Integer user2_id, String packet1_name, String packet2_name) {
		User user1=userDao.findbyid(user1_id);
		User user2=userDao.findbyid(user2_id);
		Packet packet1 = null,packet2 = null;
		for(Packet packet:user1.getPackets()){
			if(packet.getPacket_name().equals(packet1_name))
				packet1=packet;
		}
		for(Packet packet:user2.getPackets()){
			if(packet.getPacket_name().equals(packet2_name))
				packet2=packet;
		}
		friendDao.addFriend(user1, user2, packet1, packet2);
		temporaryFriendDao.deleteTemporaryFriend(user1_id, user2_id);
	}

	@Override
	public void delete(Integer user1_id, Integer user2_id) {
		User user1=new User();
		User user2=new User();
		user1.setUser_id(user1_id);
		user2.setUser_id(user2_id);
		friendDao.deleteFriend(user1, user2);
	}

	@Override
	public void movePacket(Integer user1_id, Integer user2_id, String packet1_name, String packet2_name) {
		User user1=userDao.findbyid(user1_id);
		User user2=userDao.findbyid(user2_id);
		Packet old_packet=null,new_packet=null;
		for(Packet packet:user1.getPackets()){
			if(packet.getPacket_name().equals(packet1_name))
				old_packet=packet;
		}
		for(Packet packet:user1.getPackets()){
			if(packet.getPacket_name().equals(packet2_name))
				new_packet=packet;
		}
		friendDao.movePacket(user2, old_packet, new_packet);
	}

	@Override
	public List<Friend> findFriends(Integer user_id) {
		User user=userDao.findbyid(user_id);
		return friendDao.findAllFriends(user);
	}

	@Override
	public void modifyRemarkName(Integer user1_id, Integer user2_id, String remarkname) {
		User user1=userDao.findbyid(user1_id);
		User user2=userDao.findbyid(user2_id);
		friendDao.modifyRemarkName(user1, user2, remarkname);
	}

	@Override
	public List<Friend> findPacketFriends(Integer user_id, String packet_name) {
		User user=userDao.findbyid(user_id);
		Packet packet=null;
		for(Packet packet_:user.getPackets()){
			if(packet_.getPacket_name().equals(packet_name))
				packet=packet_;
		}
		return friendDao.findFriends(user, packet);
	}

	@Override
	public void addTemporaryFriend(Integer user1_id, Integer user2_id, String packet_name) {
		TemporaryFriend temporaryFriend=new TemporaryFriend();
		temporaryFriend.setUser1_id(user1_id);
		temporaryFriend.setUser2_id(user2_id);
		temporaryFriend.setPacket_name(packet_name);
		temporaryFriendDao.addTemporaryFriend(temporaryFriend);

	}

	@Override
	public void deleteTemporaryFriend(Integer user1_id, Integer user2_id) {
		temporaryFriendDao.deleteTemporaryFriend(user1_id, user2_id);
	}

	@Override
	public List<TemporaryFriend> findTemporaryFriends(Integer user_id) {
		return temporaryFriendDao.findTemporaryFriends(user_id);
	}

	@Override
	public List<TemporaryFriend> findTemporaryFriend(Integer user1_id, Integer user2_id) {
		return temporaryFriendDao.findTemporaryFriend(user1_id, user2_id);
	}

	@Override
	public Friend findFriend(Integer user1_id, Integer user2_id) {
		User user1=new User();
		user1.setUser_id(user1_id);
		User user2=new User();
		user2.setUser_id(user2_id);
		return friendDao.findFriend(user1, user2);
	}
}

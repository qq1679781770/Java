package com.jsxnh.smartqq.dao;

import java.util.List;

import com.jsxnh.smartqq.entities.Friend;
import com.jsxnh.smartqq.entities.Packet;
import com.jsxnh.smartqq.entities.User;

public interface PacketDao {

	//查找用户分组
	public List<Packet> findpacketById(User user);
	//修改分组
	public void modifyPacket(Packet packet,String new_name);
	//查找某分组的好友
	public List<Friend> findFriendsbyPacket(Packet packet);
	
}

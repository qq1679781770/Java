package com.jsxnh.smartqq.dao;

import java.util.List;

import com.jsxnh.smartqq.entities.Friend;
import com.jsxnh.smartqq.entities.Packet;
import com.jsxnh.smartqq.entities.User;

public interface PacketDao {

	//�����û�����
	public List<Packet> findpacketById(User user);
	//�޸ķ���
	public void modifyPacket(Packet packet,String new_name);
	//����ĳ����ĺ���
	public List<Friend> findFriendsbyPacket(Packet packet);
	
}

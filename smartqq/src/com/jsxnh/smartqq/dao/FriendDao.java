package com.jsxnh.smartqq.dao;

import java.util.List;
import com.jsxnh.smartqq.entities.Friend;
import com.jsxnh.smartqq.entities.Packet;
import com.jsxnh.smartqq.entities.User;

public interface FriendDao {

	//Ѱ���û�ĳ�����������
	public List<Friend> findFriends(User user,Packet packet);
	//�������
	public void addFriend(User user1,User user2,Packet packet1,Packet packet2);
	//ɾ������
	public void deleteFriend(User user1,User user2);
	//�ƶ�����
	public void movePacket(User user,Packet old_packet, Packet packet);
	//Ѱ�Һ���
	public Friend findFriend(User user1,User user2);
	//Ѱ���û�����
	public List<Friend> findAllFriends(User user);
	//�޸ı�ע
	public void modifyRemarkName(User user1,User user2,String remarkname);
}
